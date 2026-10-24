# ДЗ_03. Карта зависимостей StPlatform

1. Разбираю сценарий «сдать домашнюю работу» через `POST /assignments/{id}/submit`.

2. Контроллеры: 
- `web/AssignmentPageController.java` - принимает веб-форму
- `api/ApiSubmissionController.java` - принимает и преобразует json  
- оба передают сдачу в один сервис в `submission/SubmissionService.java`

3. Сервисы:
-  `submission/SubmissionService.java` - проверяет правила сдачи
- `user/UserService.java` - загружает пользователя
- в проекте также есть другие сервисы `AssignmentService`, `ActivationService`, `PasswordResetService`, `ConsentService`, `StudentRemovalService`.

4. Репозитории и хранилища: 
- `UserRepository`, `AssignmentRepository`, `SubmissionRepository`, `SubmissionAttachmentRepository`, `ConsentRecordRepository` — репозитории проекта, наследующие `JpaRepository` из Spring Data JPA
- данные хранятся в PostgreSQL - указано в `application.yml` - `${DB_URL:jdbc:postgresql://localhost:5432/stplatform}`
- `AttachmentStorage` -  отдельно хранит содержимое файлов на диске

5. Трасса функции по получению студента: 
сначала в `AssignmentPageController.java.submitWork` выполняется `User student = users.byId(principal.getId());`
далее по цепочке id из сессии, затем `UserService` (`UserService.java.byId` -> `UserService.java.findById`) через `UserRepository` (`UserRepository.java.findById`) загружает пользователя из БД
- метод репозитория унаследован от `JpaRepository` 

6. Основная трасса сдачи домашней работы, после загрузки студента из п.5: 
`AssignmentPageController.submitWork` (контроллер вызывает) принимает данные формы
  -> `SubmissionService.submit` (сервис, вызванный контррллером) проверяет, что пользователь имеет роль
      -> `AssignmentRepository.findById` получает ДЗ по id
          -> БД: получить задание
      затем вызывается:
      -> `SubmissionService.ensureNotAlreadySent` проверяет, разрешена ли повторная сдача
          -> `SubmissionRepository.findFirstByStudentIdAndAssignmentIdOrderByCreatedAtDesc` ищет сдачи по id студента и задания
              -> БД: получить последнюю сдачу

7. Продолжение трассы сдачи домашней работы, после проверок из п.6:
`SubmissionService.submit` проверяет дедлайн и содержимое формы  
  -> `LinkValidator.isValid` проверяет каждую переданную ссылку
    затем сервис проверяет ограничения количества и длины ссылок  
  -> `SubmissionRepository.save` сохраняет новую сдачу  
      -> `JPA/Hibernate` -> БД: записать сдачу  

Если приложен файл:  
  -> `AttachmentStorage.store` проверяет размер файла, подготавливает безопасное имя и имя для хранения  
      -> `Files.copy` записывает содержимое файла на диск  
  -> `SubmissionAttachmentRepository.save` сохраняет метаданные файла и связь со сдачей  
      -> `JPA/Hibernate` -> БД: записать сведения о вложении  

После успешного завершения `SubmissionService.submit` транзакция фиксирует изменения в БД.

8. В этой трассе вызовы между слоями идут вниз: контроллер -> сервис -> репозитории/файловое хранилище.Внутренние методы сервиса и валидатор находятся на том же уровне.
Репозитории контроллер не вызывают.
Возврат результата наверх не является обратной зависимостью, а является передачей результата выполнения метода тому коду, который его вызвал. 

9. Но граница с веб-слоем неполная: `SubmissionService.submit` и `AttachmentStorage.store` принимают `org.springframework.web.multipart.MultipartFile`. 
Получается, сервис с правилами сдачи зависит от способа загрузки файла через веб. AttachmentStorage.store тоже принимает этот веб-тип.
Например, если сдачу захочется принимать другим способом, то полученный файл придётся преобразовывать в MultipartFile, чтобы вызвать существующий сервис.
Возможно надо было бы оставить преобразование multipart в контроллере, а сервису передавал бы только описание вложения: имя, тип, размер и источник байтов и проверку лимита 5 МБ сохранить ниже контроллера.
Это позволит иметь независимые правила сдачи и переиспользовать их и менять их без изменения зависимостей.


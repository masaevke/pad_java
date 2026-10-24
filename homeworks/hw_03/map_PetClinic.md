# PetClinic: карта зависимостей для добавления визита

## Слои

- **Веб / MVC:** `owner/VisitController.java` — обрабатывает форму визита.
- **Доменная модель:** `owner/Owner.java`, `owner/Pet.java`, `owner/Visit.java` — связывает визит с питомцем и владельцем.
- **Доступ к данным:** `owner/OwnerRepository.java` — Spring Data JPA-репозиторий; реализация предоставляется Spring.
- **Сервисный слой:** отдельного сервиса нет; контроллер вызывает репозиторий и доменную модель напрямую.

## Цепочка вызовов

При отправке формы:

`owner/VisitController.java.processNewVisitForm` → `owner/Owner.java.addVisit` → `owner/Owner.java.getPet` → `owner/Pet.java.addVisit` → `owner/OwnerRepository.java.save` → `SimpleJpaRepository.save` → `EntityManager.merge`

`OwnerRepository.save` — унаследованный метод Spring Data JPA. Сохранение владельца каскадно сохраняет связанные данные питомца и визита (`cascade = ALL`).

До отправки формы контроллер подготавливает данные визита отдельным обработчиком:

`owner/VisitController.java.loadPetWithVisit` → `owner/OwnerRepository.java.findById` → `owner/Owner.java.getPet` → `owner/Pet.java.addVisit`

## Направление зависимостей и граница

Стрелки направлены вниз от MVC-контроллера к репозиторию и доменной модели; промежуточного сервисного слоя в этом сценарии нет. Отдельный сервис между контроллером и репозиторием мог бы координировать операцию добавления визита, оставив контроллеру только HTTP и форму.

**Зачем нужна граница:** она отделяет HTTP-обработку от правил и координации доменной операции, позволяя менять веб-слой независимо.

## Источник выполнения трассировки

Трассировку выполнил агент `general-purpose` (через GitHub Copilot - при работе видел, что это была GPT 6 Luna) (задача «PetClinic visit tracer»). Точное имя агента и модель в метаданных, доступных агенту, не раскрыты.
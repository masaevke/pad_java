# Генерация Spring-каркаса

Из корня репозитория, OpenAPI Generator CLI 7.12.0:

```powershell
java -jar .tools/openapi-generator-cli-7.12.0.jar generate -i homeworks/hw_05/api.yaml -g spring -o homeworks/hw_05/service --additional-properties=useSpringBoot3=true,groupId=ru.urfu.petrovich,artifactId=petrovich-subscribers,apiPackage=ru.urfu.petrovich.api,modelPackage=ru.urfu.petrovich.dto,invokerPackage=ru.urfu.petrovich,hideGenerationTimestamp=true
```

JAR генератора: https://repo.maven.apache.org/maven2/org/openapitools/openapi-generator-cli/7.12.0/openapi-generator-cli-7.12.0.jar

Сборка и запуск при установленных JDK 17 и Maven:

```powershell
cd homeworks/hw_05/service
mvn clean verify
mvn spring-boot:run
```

Swagger UI: http://localhost:8080/swagger-ui/index.html

В `service/src/main/java/ru/urfu/petrovich/api` находятся интерфейс пяти операций
и контроллер, в `dto` — классы запросов и ответов. Методы пока возвращают
`501 Not Implemented`: это сгенерированные заглушки для последующей реализации.
Базы данных и отправки Telegram-сообщений в каркасе нет.

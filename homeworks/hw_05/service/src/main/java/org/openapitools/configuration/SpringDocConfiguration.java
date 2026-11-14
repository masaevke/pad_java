package org.openapitools.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SpringDocConfiguration {

    @Bean(name = "org.openapitools.configuration.SpringDocConfiguration.apiInfo")
    OpenAPI apiInfo() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Petrovich AI — подписчики отчётов")
                                .description("Учебный API управления подписчиками Telegram-отчётов об автопарке. Основан на api/v1/subscribers.py и публичных схемах проекта petrovich_ai. Позволяет настроить получателя и время ежедневного отчёта. Это проектируемый контракт, а не точная копия работающего сервиса: добавлен GET одного подписчика, POST и DELETE возвращают 200 вместо исходных 201 и 204; ошибки валидации представлены ответом 400. Время рассылки задаётся в часовом поясе сервиса. Аутентификация исходным разделом API не предусмотрена. 403 при создании означает запрет рассылки в указанный Telegram-чат. ")
                                .version("1.0.0")
                )
        ;
    }
}
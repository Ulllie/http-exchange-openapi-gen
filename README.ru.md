# http-exchange-openapi-gen

# Language

[EN](README.md) | RU

# Demo-проект на Spring Boot 4 для статьи:
**"HttpClient в Spring Boot 4 + OpenAPI: замена FeignClient или нет?"**

## Что это

Компактный пример, который показывает, как использовать OpenAPI Generator + Spring `HttpExchange`
(`spring-http-interface`) как альтернативу классическому `FeignClient`.

## Цели проекта

- Генерировать клиент из OpenAPI-спеки.
- Подключать его как нативный Spring HTTP interface.
- Сравнить `RestClient` и `WebClient` в одной кодовой базе.
- Показать практические нюансы (валидация, обработка ошибок, локальный WireMock-профиль).

## Что внутри

- Внешняя OpenAPI-спека: `src/main/resources/openapi/external/weather-api.yaml`
- Внутренняя OpenAPI-спека (REST API сервиса): `src/main/resources/openapi/internal/rest-api.yaml`
- Сгенерированный HTTP interface клиент `OpenMeteoApi` (`library = spring-http-interface`)
- Конфиг для `RestClient`: `WeatherRestClientConfig`
- Конфиг для `WebClient`: `WeatherWebClientConfig` (профиль `webclient`)
- Локальная интеграция с WireMock: профиль `local` + `sandbox/docker-compose.yml`

## Быстрый старт

1) Сгенерировать код и собрать проект:

```bash
./mvnw clean generate-sources package
```

2) Запустить приложение (по умолчанию `RestClient` + реальный Open-Meteo):

```bash
./mvnw spring-boot:run
```

3) Проверить endpoint:

```bash
curl "http://localhost:8080/api/weather?latitude=55.75&longitude=37.62"
```

## Локальный режим (WireMock)

```bash
docker compose -f sandbox/docker-compose.yml up -d
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

Для `WebClient` + WireMock:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=webclient,local
```

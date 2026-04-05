# Language

EN | [RU](README.ru.md)

# Spring Boot 4 demo project for the article:
**"HttpClient in Spring Boot 4 + OpenAPI: Feign replacement or not?"**

## What this project is

A compact demo showing how to use OpenAPI Generator + Spring `HttpExchange`
(`spring-http-interface`) as an alternative to a traditional `FeignClient` setup.

## Goals

- Generate a client from an OpenAPI spec.
- Wire it as a native Spring HTTP interface.
- Compare `RestClient` and `WebClient` in one codebase.
- Show practical details (validation, error handling, local WireMock profile).

## What's inside

- External OpenAPI spec: `src/main/resources/openapi/external/weather-api.yaml`
- Internal OpenAPI spec (service REST API): `src/main/resources/openapi/internal/rest-api.yaml`
- Generated `OpenMeteoApi` HTTP interface client (`library = spring-http-interface`)
- `RestClient` wiring: `WeatherRestClientConfig`
- `WebClient` wiring: `WeatherWebClientConfig` (`webclient` profile)
- Local WireMock setup: `local` profile + `sandbox/docker-compose.yml`

## Quick start

1) Generate sources and build:

```bash
./mvnw clean generate-sources package
```

2) Run app (default: `RestClient` + real Open-Meteo):

```bash
./mvnw spring-boot:run
```

3) Call endpoint:

```bash
curl "http://localhost:8080/api/weather?latitude=55.75&longitude=37.62"
```

## Local mode (WireMock)

```bash
docker compose -f sandbox/docker-compose.yml up -d
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

For `WebClient` + WireMock:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=webclient,local
```
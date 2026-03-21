# Weather API Test Automation (Serenity + Rest Assured)

## Overview
This project contains automated API tests for the Weatherbit REST API.
The goal is to improve visibility into API quality and provide fast, repeatable checks.

## Acceptance Criteria Covered
- **AC1**: Retrieve current weather data for multiple major international cities.
- **AC3**: Programmatically identify which Australian capital city is currently the warmest.
  (Optionally add AC2/AC4 in the roadmap below.)

## Tech Stack
- Java 17
- Maven Wrapper (mvnw)
- Rest Assured
- Serenity BDD for BDD-style reporting
- AssertJ for assertions

## Project Structure
- `src/test/java/.../tests` – test classes (Serenity/JUnit)
- `src/test/java/.../steps` – Serenity Steps
- `src/test/java/.../support/client` – API client used by tests
- `src/test/java/.../support/service` – reusable service logic (e.g., temperature comparison)

> Note: Client/service classes are test-support code used to keep tests readable and maintainable.

## Prerequisites
- Java 17
- A Weatherbit API key

> Note: Maven is not required locally; the Maven Wrapper (`mvnw`) will download and use the appropriate Maven version automatically.

## How to Run Tests
`WEATHER_API_KEY` is required. If it is not set, the tests will fail fast with an explicit error message.

### Windows (PowerShell)
```powershell
$env:WEATHER_API_KEY="<YOUR_KEY>"
.\mvnw.cmd clean verify
```

### macOS / Linux
```shell
export WEATHER_API_KEY="<YOUR_KEY>"
./mvnw clean verify
```

## Test Report
Serenity generates an HTML report after execution:
- target/site/serenity/index.html

## CI
This project uses GitHub Actions (ubuntu-latest):

- Pull Requests: build only (no external API calls)
  - Executes: `./mvnw -DskipTests package`

- main branch: integration run + report
  - Executes: `./mvnw clean verify`
  - Uploads Serenity report as an artifact: `target/site/serenity/`
  - Requires WEATHER_API_KEY in GitHub Secrets

## Test Design Notes
- These tests call a real external API, so transient failures may occur (e.g., rate limits, 5xx).
- Assertions focus on stable, contract-level checks (status code, presence of key fields, valid lat/lon ranges).
- For AC3 (warmest city), the framework uses a fail-late strategy: it queries all cities, collects per-city failures (city name + reason), and throws a single aggregated exception at the end.
- For AC3, these cities are covered as Australian Capitals:
> Sydney, Melbourne, Brisbane, Perth, Adelaide, Canberra, Hobart, Darwin

## Roadmap / Future Improvements
- [x] Add WeatherConfig class for URL and API key.
- Add AC2 tests (coordinates-based validation).
- Add AC4 tests (read US states from a metadata input file and compute coldest state).
- Externalise test data to src/test/resources (JSON/CSV) for data-driven tests.
- Introduce retries for server errors to reduce flakiness.
- Migrate to Open-Meteo (WeatherBit Business Trial Expires on 2026-04-03)
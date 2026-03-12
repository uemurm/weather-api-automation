# foo

## Basics
> I externalized the API key using environment variables to avoid hardcoding sensitive credentials and to support different environments.

or
> I centralized the base URI configuration and externalized credentials to improve maintainability and security.

## AC1
> For AC1, I validated not only the HTTP status code but also ensured that the response payload contained valid weather data for each requested city.

or in short:
> I included city-level validation to ensure data integrity rather than only checking for a successful response.

> I ensured AC1 validates both HTTP status and response payload integrity across multiple cities, while externalizing credentials for security and maintainability.

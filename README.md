# testvault

## Setup

Start mariadb and create a testvault database with its associated user (or use the given docker compose file).

Fill in the database connection information in the `src/resources/application.yml` file:

```yaml
spring:
  # ...
  datasource:
    url: jdbc:mariadb://localhost:3306/testvault
    username: <USERNAME>
    password: <PASSWORD>
  # ...
```

## Run

Start the application with:

```shell
./gradlew bootRun
```
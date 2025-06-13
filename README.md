# testvault

## Setup

Start vault/bao and mariadb and create a testvault database with its associated user (or use the given docker compose file).

Store the database connection information in vault/bao:

```shell
bao kv put secret/testvault DATABASE_HOST=<HOST> DATABASE_PORT=<PORT> DATABASE_USER=<USER> DATABASE_PASS=<PASS>
```

Add the vault/bao token in the `src/resources/application.yml` file:

```yaml
spring:
  # ...
  cloud.vault:
      # ...
      token: <TOKEN>
  # ...
```

## Run

Start the application with:

```shell
./gradlew bootRun
```
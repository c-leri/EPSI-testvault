# testvault

This project needs jdk 21 or higher.

## Setup

Start vault/bao and mariadb and create a testvault database with its associated user (or use the given docker compose file).

Store the database connection information in vault/bao:

```shell
bao kv put secret/testvault DATABASE_HOST="<HOST>" DATABASE_PORT="<PORT>" DATABASE_USER="<USER>" DATABASE_PASS="<PASS>"
```

Store the connection information of the first user created by the application in vault/bao:

```shell
bao kv patch secret/testvault TESTVAULT_USERNAME="<USER>" TESTVAULT_PASSWORD="<PASS>"
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

You can now access the application's generated swagger at <http://localhost:8080/swagger-ui/index.html>.

## Usage

You can login using the `/login` endpoint in the swagger or via the form at <http://localhost:8080/login>.

After that you can use all the endpoints listed in the swagger file, depending on your user's roles.
The base user has `ADMIN` and `USER` roles.
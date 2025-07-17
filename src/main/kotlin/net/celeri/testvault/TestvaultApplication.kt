package net.celeri.testvault

import net.celeri.testvault.enums.Role
import net.celeri.testvault.models.UserCreate
import net.celeri.testvault.services.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TestvaultApplication(
    private val userService: UserService,
    @Value("\${app.base-user.username}") private val baseUserName: String,
    @Value("\${app.base-user.password}") private val baseUserPassword: String?,
) : ApplicationRunner {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun run(args: ApplicationArguments?) {
        if (baseUserPassword == null || baseUserPassword.isEmpty()) {
            throw IllegalStateException("Please provide a password for the application's base user via the TESTVAULT_PASSWORD env variable")
        }

        try {
            userService.createUser(UserCreate(baseUserName, baseUserPassword, setOf(Role.USER, Role.ADMIN)))
            logger.info("base user created")
        } catch (_: IllegalStateException) {
            logger.info("base user already created")
        }
    }
}

fun main(args: Array<String>) {
    runApplication<TestvaultApplication>(*args)
}

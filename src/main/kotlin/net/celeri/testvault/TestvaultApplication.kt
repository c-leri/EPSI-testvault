package net.celeri.testvault

import net.celeri.testvault.enums.Role
import net.celeri.testvault.models.UserCreate
import net.celeri.testvault.services.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TestvaultApplication(
    private val userService: UserService,
) : ApplicationRunner {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun run(args: ApplicationArguments?) {
        try {
            userService.createUser(UserCreate("root", "root", setOf(Role.USER, Role.ADMIN)))
            logger.info("root user created")
        } catch (e: IllegalStateException) {
            logger.info("root user already created")
        }
    }
}

fun main(args: Array<String>) {
    runApplication<TestvaultApplication>(*args)
}

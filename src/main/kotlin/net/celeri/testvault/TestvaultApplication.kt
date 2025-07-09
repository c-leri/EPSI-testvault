package net.celeri.testvault

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TestvaultApplication

fun main(args: Array<String>) {
    runApplication<TestvaultApplication>(*args)
}

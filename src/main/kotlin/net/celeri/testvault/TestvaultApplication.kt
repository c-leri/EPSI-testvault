package net.celeri.testvault

import net.celeri.testvault.entities.LivreEntity
import net.celeri.testvault.repositories.LivreRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TestvaultApplication(
    private val livreRepository: LivreRepository,
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        livreRepository.saveAll(
            listOf(
                LivreEntity("Les Misérables"),
                LivreEntity("Astérix le Gaulois"),
            )
        )

        println(
            livreRepository.findAll()
        )
    }
}

fun main(args: Array<String>) {
    runApplication<TestvaultApplication>(*args)
}

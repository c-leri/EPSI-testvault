package net.celeri.testvault.services

import net.celeri.testvault.entities.LivreEntity
import net.celeri.testvault.mappers.LivreMapper
import net.celeri.testvault.models.Livre
import net.celeri.testvault.models.LivreCreate
import net.celeri.testvault.models.LivreUpdate
import net.celeri.testvault.repositories.LivreRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class LivreService(
    private val livreRepository: LivreRepository,
    private val livreMapper: LivreMapper,
) {
    fun getLivres(): List<Livre> {
        val livreEntities = livreRepository.findAll()

        return livreMapper.fromEntities(livreEntities)
    }

    fun createLivre(create: LivreCreate): Livre {
        val livreEntity = livreMapper.fromCreate(create)

        livreRepository.save(livreEntity)

        return livreMapper.fromEntity(livreEntity)
    }

    fun getLivreById(id: Int): Livre {
        val livreEntity = getLivreEntityById(id)

        return livreMapper.fromEntity(livreEntity)
    }

    fun updateLivre(id: Int, update: LivreUpdate) {
        val livreEntity = getLivreEntityById(id)

        livreMapper.updateEntity(update, livreEntity)

        livreRepository.save(livreEntity)
    }

    fun deleteLivre(id: Int) {
        val livreEntity = getLivreEntityById(id)

        livreRepository.delete(livreEntity)
    }

    @Throws(ResponseStatusException::class)
    private fun getLivreEntityById(id: Int): LivreEntity {
        val livreEntity = livreRepository.findById(id)

        if (livreEntity.isEmpty) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }

        return livreEntity.get()
    }
}
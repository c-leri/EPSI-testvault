package net.celeri.testvault.mappers

import net.celeri.testvault.entities.LivreEntity
import net.celeri.testvault.models.Livre
import net.celeri.testvault.models.LivreCreate
import net.celeri.testvault.models.LivreUpdate
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget

@Mapper
interface LivreMapper {
    fun fromEntity(entity: LivreEntity): Livre

    fun fromEntities(entities: Collection<LivreEntity>): List<Livre>

    fun fromCreate(create: LivreCreate): LivreEntity

    fun updateEntity(update: LivreUpdate, @MappingTarget entity: LivreEntity)
}
package net.celeri.testvault.repositories

import net.celeri.testvault.entities.LivreEntity
import org.springframework.data.jpa.repository.JpaRepository

interface LivreRepository : JpaRepository<LivreEntity, Int>
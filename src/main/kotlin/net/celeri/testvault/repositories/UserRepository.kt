package net.celeri.testvault.repositories

import net.celeri.testvault.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Int> {
    fun existsByUsername(username: String?): Boolean

    fun findUserByUsername(username: String?): UserEntity?
}
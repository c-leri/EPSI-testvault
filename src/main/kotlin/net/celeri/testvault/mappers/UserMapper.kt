package net.celeri.testvault.mappers

import net.celeri.testvault.entities.UserEntity
import net.celeri.testvault.models.User
import net.celeri.testvault.models.UserCreate
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder

@Mapper
abstract class UserMapper {
    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    abstract fun fromEntity(entity: UserEntity): User

    abstract fun fromEntities(entities: Collection<UserEntity>): List<User>

    @Mapping(target = "password", source = "password", qualifiedByName = ["encodePassword"])
    abstract fun fromCreate(create: UserCreate): UserEntity

    @Named("encodePassword")
    fun encodePassword(password: String): String {
        return passwordEncoder.encode(password)
    }
}
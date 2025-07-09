package net.celeri.testvault.services

import net.celeri.testvault.entities.UserEntity
import net.celeri.testvault.mappers.UserMapper
import net.celeri.testvault.models.User
import net.celeri.testvault.models.UserCreate
import net.celeri.testvault.repositories.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
) : UserDetailsService {
    fun getUsers(): List<User> {
        val userEntities = userRepository.findAll();

        return userMapper.fromEntities(userEntities);
    }

    fun createUser(create: UserCreate): User {
        if (userRepository.existsByUsername(create.username)) {
            throw IllegalStateException("User already exists")
        }

        val userEntity = userMapper.fromCreate(create)

        userRepository.save(userEntity)

        return userMapper.fromEntity(userEntity)
    }

    fun getUserById(id: Int): User {
        val userEntity = getUserEntityById(id)

        return userMapper.fromEntity(userEntity)
    }

    override fun loadUserByUsername(username: String?): UserDetails? {
        val user = userRepository.findUserByUsername(username);

        if (user == null) {
            throw UsernameNotFoundException("Username not found")
        }

        return user
    }

    fun deleteUser(id: Int) {
        val userEntity = getUserEntityById(id)

        userRepository.delete(userEntity)
    }

    @Throws(ResponseStatusException::class)
    private fun getUserEntityById(id: Int): UserEntity {
        val userEntity = userRepository.findById(id)

        if (userEntity.isEmpty) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }

        return userEntity.get()
    }
}
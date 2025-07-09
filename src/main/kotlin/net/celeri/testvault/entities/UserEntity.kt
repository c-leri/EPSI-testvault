package net.celeri.testvault.entities

import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import net.celeri.testvault.enums.Role
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
class UserEntity(
    @Column(nullable = false, unique = true)
    private var username: String,
    @Column(nullable = false)
    private var password: String,
    @ElementCollection(targetClass = Role::class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_role")
    val roles: Set<Role> = HashSet(),
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority?>? {
        return roles.map { SimpleGrantedAuthority("ROLE_${it.value}") }
    }

    override fun getUsername(): String = username

    override fun getPassword(): String = password

    fun setUsername(username: String) {
        this.username = username
    }

    fun setPassword(password: String) {
        this.password = password
    }
}
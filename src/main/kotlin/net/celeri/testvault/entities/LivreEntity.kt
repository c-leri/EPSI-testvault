package net.celeri.testvault.entities

import jakarta.persistence.*

@Entity
data class LivreEntity(
    @Column(nullable = false)
    val titre: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
)
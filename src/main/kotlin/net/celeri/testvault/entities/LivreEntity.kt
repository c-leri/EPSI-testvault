package net.celeri.testvault.entities

import jakarta.persistence.*

@Entity
class LivreEntity(
    @Column(nullable = false)
    var titre: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
)
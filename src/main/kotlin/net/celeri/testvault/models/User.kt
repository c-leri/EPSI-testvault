package net.celeri.testvault.models

import net.celeri.testvault.enums.Role

class User(
    val id: Int,
    val username: String,
    val roles: Set<Role> = HashSet()
)
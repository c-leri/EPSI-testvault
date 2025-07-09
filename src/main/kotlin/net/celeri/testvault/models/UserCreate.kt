package net.celeri.testvault.models

import net.celeri.testvault.enums.Role

class UserCreate(
    var username: String,
    var password: String,
    val roles: Set<Role> = HashSet(),
)
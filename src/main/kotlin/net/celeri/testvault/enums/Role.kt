package net.celeri.testvault.enums

import net.celeri.testvault.constants.RoleValues

enum class Role(
    val value: String
) {
    USER(RoleValues.USER),
    ADMIN(RoleValues.ADMIN)
}

package net.celeri.testvault.controllers

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LogoutController {
    // Only here to add the logout endpoint to the generated swagger,
    // the actual logout is handled by spring security
    @PostMapping("/logout")
    fun logout() {}
}
package net.celeri.testvault.controllers

import net.celeri.testvault.constants.RoleValues
import net.celeri.testvault.models.User
import net.celeri.testvault.models.UserCreate
import net.celeri.testvault.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService,
) {
    @PreAuthorize("hasRole('${RoleValues.ADMIN}')")
    @GetMapping
    fun getUsers(): ResponseEntity<List<User>> {
        val users = userService.getUsers()

        return ResponseEntity.ok(users)
    }

    @PreAuthorize("hasRole('${RoleValues.ADMIN}')")
    @PostMapping
    fun createUser(@RequestBody create: UserCreate): ResponseEntity<Void> {
        val user = userService.createUser(create)

        val location = MvcUriComponentsBuilder
            .fromMethodCall(
                MvcUriComponentsBuilder
                    .on(javaClass)
                    .getUser(user.id)
            )
            .build()
            .toUri()

        return ResponseEntity.created(location).build()
    }

    @PreAuthorize("hasRole('${RoleValues.ADMIN}')")
    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Int): ResponseEntity<User> {
        val user = userService.getUserById(id)

        return ResponseEntity.ok(user)
    }

    @PreAuthorize("hasRole('${RoleValues.ADMIN}')")
    @DeleteMapping("/{id}")
    fun deleteLivre(@PathVariable id: Int): ResponseEntity<Void> {
        userService.deleteUser(id)

        return ResponseEntity.noContent().build()
    }
}

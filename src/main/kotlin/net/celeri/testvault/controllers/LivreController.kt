package net.celeri.testvault.controllers

import net.celeri.testvault.constants.RoleValues
import net.celeri.testvault.models.Livre
import net.celeri.testvault.models.LivreCreate
import net.celeri.testvault.models.LivreUpdate
import net.celeri.testvault.services.LivreService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder


@RestController
@RequestMapping("/livres")
class LivreController(
    private val livreService: LivreService,
) {
    @PreAuthorize("hasRole('${RoleValues.USER}')")
    @GetMapping
    fun getLivres(): ResponseEntity<List<Livre>> {
        val livres = livreService.getLivres()

        return ResponseEntity.ok(livres)
    }

    @PreAuthorize("hasRole('${RoleValues.ADMIN}')")
    @PostMapping
    fun createLivre(@RequestBody create: LivreCreate): ResponseEntity<Void> {
        val livre = livreService.createLivre(create)

        val location = MvcUriComponentsBuilder
            .fromMethodCall(
                MvcUriComponentsBuilder
                    .on(javaClass)
                    .getLivre(livre.id)
            )
            .build()
            .toUri()

        return ResponseEntity.created(location).build()
    }

    @PreAuthorize("hasRole('${RoleValues.USER}')")
    @GetMapping("/{id}")
    fun getLivre(@PathVariable id: Int): ResponseEntity<Livre> {
        val livre = livreService.getLivreById(id)

        return ResponseEntity.ok(livre)
    }

    @PreAuthorize("hasRole('${RoleValues.ADMIN}')")
    @PutMapping("/{id}")
    fun updateLivre(@PathVariable id: Int, @RequestBody update: LivreUpdate): ResponseEntity<Void> {
        livreService.updateLivre(id, update)

        return ResponseEntity.noContent().build()
    }

    @PreAuthorize("hasRole('${RoleValues.ADMIN}')")
    @DeleteMapping("/{id}")
    fun deleteLivre(@PathVariable id: Int): ResponseEntity<Void> {
        livreService.deleteLivre(id)

        return ResponseEntity.noContent().build()
    }
}
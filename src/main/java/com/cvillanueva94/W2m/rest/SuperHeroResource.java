package com.cvillanueva94.W2m.rest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cvillanueva94.W2m.model.SuperHeroDTO;
import com.cvillanueva94.W2m.service.SuperHeroService;

@RestController
@RequestMapping(value = "/api/superHeroes", produces = MediaType.APPLICATION_JSON_VALUE)
public class SuperHeroResource {

    private final SuperHeroService superHeroService;

    public SuperHeroResource(final SuperHeroService superHeroService) {
        this.superHeroService = superHeroService;
    }

    @GetMapping
    public ResponseEntity<List<SuperHeroDTO>> getAllSuperHeroes(
            @RequestParam(name = "name", defaultValue = "") String name) {
        return ResponseEntity.ok(superHeroService.findAll(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuperHeroDTO> getSuperHero(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(superHeroService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createSuperHero(
            @RequestBody @Valid final SuperHeroDTO superHeroDTO) {
        final Long createdId = superHeroService.create(superHeroDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateSuperHero(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final SuperHeroDTO superHeroDTO) {
        superHeroService.update(id, superHeroDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteSuperHero(@PathVariable(name = "id") final Long id) {
        superHeroService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

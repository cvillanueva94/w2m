package com.cvillanueva94.W2m.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cvillanueva94.W2m.domain.SuperHero;
import com.cvillanueva94.W2m.model.SuperHeroDTO;
import com.cvillanueva94.W2m.repos.SuperHeroRepository;

import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;

class SuperHeroServiceTest {
    @Mock
    private SuperHeroRepository superHeroRepository;
    @InjectMocks
    private SuperHeroService superHeroService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindAll() {
        // Arrange
        String name = "man";
        SuperHero superHero1 = new SuperHero();
        superHero1.setName("Superman");
        SuperHero superHero2 = new SuperHero();
        superHero2.setName("Spiderman");
        List<SuperHero> superHeroes = Arrays.asList(superHero1, superHero2);
        when(superHeroRepository.findByNameContaining(name)).thenReturn(superHeroes);
        // Act
        List<SuperHeroDTO> result = superHeroService.findAll(name);
        // Assert
        assertEquals(2, result.size());
        assertEquals("Superman", result.get(0).getName());
        assertEquals("Spiderman", result.get(1).getName());
    }
}
package com.cvillanueva94.W2m.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cvillanueva94.W2m.domain.SuperHero;

public interface SuperHeroRepository extends JpaRepository<SuperHero, Long> {
    List<SuperHero> findByNameContaining(String name);
}

package com.cvillanueva94.W2m.service;

import java.util.List;
// import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cvillanueva94.W2m.domain.SuperHero;
import com.cvillanueva94.W2m.model.SuperHeroDTO;
import com.cvillanueva94.W2m.repos.SuperHeroRepository;
import com.cvillanueva94.W2m.util.NotFoundException;

@Service
public class SuperHeroService {

    private final SuperHeroRepository superHeroRepository;

    public SuperHeroService(final SuperHeroRepository superHeroRepository) {
        this.superHeroRepository = superHeroRepository;
    }

    public List<SuperHeroDTO> findAll(final String name) {
        // final List<SuperHero> superHeroes =
        // superHeroRepository.findAll(Sort.by("id"));
        final List<SuperHero> superHeroes = superHeroRepository.findByNameContaining(name);
        return superHeroes.stream()
                .map(superHero -> mapToDTO(superHero, new SuperHeroDTO()))
                .toList();
    }

    public SuperHeroDTO get(final Long id) {
        return superHeroRepository.findById(id)
                .map(superHero -> mapToDTO(superHero, new SuperHeroDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final SuperHeroDTO superHeroDTO) {
        final SuperHero superHero = new SuperHero();
        mapToEntity(superHeroDTO, superHero);
        return superHeroRepository.save(superHero).getId();
    }

    public void update(final Long id, final SuperHeroDTO superHeroDTO) {
        final SuperHero superHero = superHeroRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(superHeroDTO, superHero);
        superHeroRepository.save(superHero);
    }

    public void delete(final Long id) {
        superHeroRepository.deleteById(id);
    }

    private SuperHeroDTO mapToDTO(final SuperHero superHero, final SuperHeroDTO superHeroDTO) {
        superHeroDTO.setId(superHero.getId());
        superHeroDTO.setName(superHero.getName());
        return superHeroDTO;
    }

    private SuperHero mapToEntity(final SuperHeroDTO superHeroDTO, final SuperHero superHero) {
        superHero.setName(superHeroDTO.getName());
        return superHero;
    }

}

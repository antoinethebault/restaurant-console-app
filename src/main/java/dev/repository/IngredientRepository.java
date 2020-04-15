package dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.dto.IngredientDto;
import dev.entite.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
	Optional<IngredientDto> findByNom(String nom);
}

package dev.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.config.RepositoryConfig;
import dev.dto.IngredientDto;

@SpringJUnitConfig({ RepositoryConfig.class })
class IngredientRepositoryIntegrationTest {

	private static final Logger LOGGER = Logger.getLogger(IngredientRepositoryIntegrationTest.class.getName());

	@Autowired
	private IngredientRepository ingredientRepository;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testFindByNom() {
		LOGGER.info("Etant donne que l'on recupere un ingredient via le repository");
		IngredientDto ingredientDto = ingredientRepository.findByNom("Beurre").get();
		assertThat(ingredientDto.getNom()).isEqualTo("Beurre");
	}

}

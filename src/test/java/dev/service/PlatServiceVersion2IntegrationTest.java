package dev.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.dao.PlatDaoMemoire;
import dev.entite.Plat;
import dev.exception.PlatException;

@SpringJUnitConfig({ PlatServiceVersion2.class, PlatDaoMemoire.class })
@ActiveProfiles({ "memoire", "version2" })
class PlatServiceVersion2IntegrationTest {

	private static final Logger LOGGER = Logger.getLogger(PlatServiceVersion2IntegrationTest.class.getName());

	@Autowired
	PlatServiceVersion2 platService;

	@Test
	void testAjouterPlatCasPassant() {
		LOGGER.info("Etant donne qu'un plat est ajoute");
		platService.ajouterPlat("Plat 1", 1500);

		LOGGER.info("Lorsque je recupere le plat");
		List<Plat> listePlats = platService.listerPlats();
		Plat platFourni = listePlats.get(0);

		LOGGER.info("Alors le plat recupere doit etre le meme que celui ajoute");
		Plat platAttendu = new Plat("Plat 1", 1500);
		assertThat(platFourni).isEqualTo(platAttendu);

	}

	@Test
	void testAjouterPlatPrixInvalide() {
		assertThatThrownBy(() -> {
			platService.ajouterPlat("Plat 2", 1000);
		}).isInstanceOf(PlatException.class).hasMessage("le prix d'un plat doit être supérieur à 10 €");
	}

}

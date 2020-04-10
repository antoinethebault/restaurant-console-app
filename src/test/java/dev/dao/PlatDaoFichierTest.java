package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.entite.Plat;
import dev.service.PlatServiceVersion2;

@SpringJUnitConfig({ PlatDaoFichier.class, PlatServiceVersion2.class })
@TestPropertySource("classpath:test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PlatDaoFichierTest {

	private static final Logger LOGGER = Logger.getLogger(PlatDaoFichierTest.class.getName());

	@Autowired
	PlatServiceVersion2 platService;

	@Test
	void testAjouterPlatSauvegardeOk() {
		LOGGER.info("Etant donne qu'un plat est ajoute");
		platService.ajouterPlat("Plat 1", 1500);

		LOGGER.info("Lorsque je recupere le plat");
		List<Plat> listePlats = platService.listerPlats();
		Plat platFourni = listePlats.get(0);

		LOGGER.info("Alors le plat recupere doit etre le meme que celui ajoute");
		Plat platAttendu = new Plat("Plat 1", 1500);
		assertThat(platFourni).isEqualTo(platAttendu);

		LOGGER.info("On verifie que la taille de la liste soit de 1");
		assertThat(this.platService.listerPlats().size()).isEqualTo(1);

	}

	@Test
	void testAjouterPlat2SauvegardeOk() {
		LOGGER.info("Etant donne qu'un plat est ajoute");
		platService.ajouterPlat("Plat 2", 2000);

		LOGGER.info("Lorsque je recupere le plat");
		List<Plat> listePlats = platService.listerPlats();
		Plat platFourni = listePlats.get(0);

		LOGGER.info("Alors le plat recupere doit etre le meme que celui ajoute");
		Plat platAttendu = new Plat("Plat 2", 2000);
		assertThat(platFourni).isEqualTo(platAttendu);

		LOGGER.info("On verifie que la taille de la liste soit de 1");
		assertThat(this.platService.listerPlats().size()).isEqualTo(1);

	}

}

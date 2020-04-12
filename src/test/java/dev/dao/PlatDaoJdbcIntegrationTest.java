package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.config.JdbcTestConfig;
import dev.entite.Plat;
import dev.mapper.PlatMapper;

@SpringJUnitConfig({ JdbcTestConfig.class, PlatDaoJdbc.class })
@ActiveProfiles("jdbc")
public class PlatDaoJdbcIntegrationTest {

	private static final Logger LOGGER = Logger.getLogger(PlatDaoJdbcIntegrationTest.class.getName());

	@Autowired
	PlatDaoJdbc platDaoJdbc;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Test
	void listePlatsNonVide() {
		LOGGER.info("Etant donne que la table de type H2 est alimentee au lancement de la configuration");
		LOGGER.info("Lorsque on liste les plats de la table");
		List<Plat> listePlats = platDaoJdbc.listerPlats();
		LOGGER.info("Alors la liste n'est pas vide");
		assertFalse(listePlats.isEmpty());
	}

	@Test
	void testAjouterPlat() {
		LOGGER.info("Etant donne que l'on ajoute un plat a la table");
		Plat platAjoute = new Plat("plat 1", 1200);
		platDaoJdbc.ajouterPlat(platAjoute.getNom(), platAjoute.getPrixEnCentimesEuros());

		LOGGER.info("Lorsque l'on recupere le plat ajoute a la table");
		String sql = "SELECT * FROM plats where nom=?";
		Plat platRecupere = jdbcTemplate.queryForObject(sql, new Object[] { "plat 1" }, new PlatMapper());

		LOGGER.info("Alors le plat ajoute correspond au plat recupere");
		assertThat(platAjoute).isEqualTo(platRecupere);
	}

}

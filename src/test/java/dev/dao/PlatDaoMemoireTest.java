package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.entite.Plat;

class PlatDaoMemoireTest {
	private PlatDaoMemoire platDaoMemoire;

	@BeforeEach
	void setUp() {
		this.platDaoMemoire = new PlatDaoMemoire();
	}

	@Test
	void testListerPlatsVideALInitialisation() {
		assertThat(platDaoMemoire.listerPlats()).isEmpty();
	}

	@Test
	void testAjouterPlatCasPassants() {
		// on cree une liste de plats qu'on s'attend a recuperer
		List<Plat> listePlats = new ArrayList<>();
		listePlats.add(new Plat("Plat 1", 1000));
		listePlats.add(new Plat("Plat 2", 1200));

		// on ajoute les plats a la memoire
		platDaoMemoire.ajouterPlat("Plat 1", 1000);
		platDaoMemoire.ajouterPlat("Plat 2", 1200);

		// on compare les deux listes qui doivent etre identiques
		assertThat(platDaoMemoire.listerPlats()).isEqualTo(listePlats);

	}
}

package dev.dao;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import dev.entite.Plat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		List<Plat> listePlats = new ArrayList<>();
		listePlats.add(new Plat("Plat 1", 1000));
		listePlats.add(new Plat("Plat 2", 1200));
		
		platDaoMemoire.ajouterPlat("Plat 1", 1000);
		platDaoMemoire.ajouterPlat("Plat 2", 1200);
		
		assertThat(platDaoMemoire.listerPlats()).isEqualTo(listePlats);
		
	}
}

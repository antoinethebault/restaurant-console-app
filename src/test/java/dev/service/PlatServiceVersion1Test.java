package dev.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.dao.IPlatDao;
import dev.exception.PlatException;

class PlatServiceVersion1Test {

	IPlatDao platDao;
	PlatServiceVersion1 platService;

	@BeforeEach
	public void setUp() {
		platDao = mock(IPlatDao.class);
		platService = new PlatServiceVersion1(platDao);
	}

	@Test
	void testAjouterPlatNomInvalide() {

		assertThatThrownBy(() -> {
			platService.ajouterPlat("xx", 1000);
		}).isInstanceOf(PlatException.class).hasMessage("un plat doit avoir un nom de plus de 3 caractères");

	}

	@Test
	void testAjouterPlatPrixInvalide() {

		assertThatThrownBy(() -> {
			platService.ajouterPlat("Plat", 300);
		}).isInstanceOf(PlatException.class).hasMessage("le prix d'un plat doit être supérieur à 5 €");

	}

	@Test
	void testAjouterPlatValide() {

		platService.ajouterPlat("Plat", 600);
		verify(platDao).ajouterPlat("Plat", 600);

	}
}

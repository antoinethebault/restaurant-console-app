package dev.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import dev.config.RepositoryConfig;
import dev.dao.PlatDaoJdbcIntegrationTest;
import dev.entite.Plat;
import dev.mapper.PlatMapper;

@SpringJUnitConfig({ RepositoryConfig.class })
class PlatRepositoryIntegrationTest {

	private static final Logger LOGGER = Logger.getLogger(PlatDaoJdbcIntegrationTest.class.getName());

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private PlatRepository platRepository;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testFindAll() {
		LOGGER.info("Etant donne qu'on recupere les plats a partir du repository");
		List<Plat> listePlats = platRepository.findAll();

		LOGGER.info("Lorsque l'on recupere les plats via jdbc");
		String sql = "SELECT * FROM plats";
		List<Plat> listePlatsRecuperes = jdbcTemplate.query(sql, new PlatMapper());

		LOGGER.info("Alors les deux listes recuperees sont identiques");
		assertThat(listePlats).isEqualTo(listePlatsRecuperes);

	}

	@Test
	void testFindAllSortAsc() {
		LOGGER.info("Etant donne qu'on recupere les plats a partir du repository");
		Sort tri = Sort.sort(Plat.class).by(Plat::getPrixEnCentimesEuros).ascending();
		List<Plat> listePlats = platRepository.findAll(tri);

		LOGGER.info("Lorsque l'on recupere les plats via jdbc");
		String sql = "SELECT * FROM plats ORDER BY prixEnCentimesEuros ASC";
		List<Plat> listePlatsRecuperes = jdbcTemplate.query(sql, new PlatMapper());

		LOGGER.info("Alors les deux listes recuperees sont identiques");
		assertThat(listePlats).isEqualTo(listePlatsRecuperes);
	}

	@Test
	void testFindAllSortDesc() {
		LOGGER.info("Etant donne qu'on recupere les plats a partir du repository");
		Sort tri = Sort.sort(Plat.class).by(Plat::getPrixEnCentimesEuros).descending();
		List<Plat> listePlats = platRepository.findAll(tri);

		LOGGER.info("Lorsque l'on recupere les plats via jdbc");
		String sql = "SELECT * FROM plats ORDER BY prixEnCentimesEuros DESC";
		List<Plat> listePlatsRecuperes = jdbcTemplate.query(sql, new PlatMapper());

		LOGGER.info("Alors les deux listes recuperees sont identiques");
		assertThat(listePlats).isEqualTo(listePlatsRecuperes);
	}

	@Test
	void testFindAllPageable() {
		LOGGER.info("Etant donne qu'on recupere les plats a partir du repository");
		Sort tri = Sort.sort(Plat.class).by(Plat::getNom).ascending();
		Pageable pageable = PageRequest.of(0, 2, tri);
		Page<Plat> page = platRepository.findAll(pageable);
		List<Plat> listePlats = page.toList();

		LOGGER.info("Lorsque l'on veut comparer avec les resultats attendus");

		LOGGER.info("Alors les valeurs sont bien egales aux resultats attendus");
		assertThat(listePlats.get(0).getNom()).isEqualTo("Blanquette de veau");
		assertThat(listePlats.get(1).getNom()).isEqualTo("Couscous");
	}

	@Test
	void testFindById() {
		LOGGER.info("Etant donne qu'on recupere le plat a partir du repository");
		Plat plat = platRepository.findById(1).get();

		LOGGER.info("Lorsque l'on recupere le plat via jdbc");
		String sql = "SELECT * FROM plats WHERE id=1";
		Plat platRecupere = jdbcTemplate.queryForObject(sql, new PlatMapper());

		LOGGER.info("Alors les deux plats sont identiques");
		assertThat(plat).isEqualTo(platRecupere);
	}

	@Test
	@Transactional
	void testGetOne() {
		LOGGER.info("Etant donne qu'on cree une entite");
		Plat plat = platRepository.getOne(1);

		LOGGER.info("Lorsque l'on recupere le plat via jdbc");
		String sql = "SELECT * FROM plats WHERE id=1";
		Plat platRecupere = jdbcTemplate.queryForObject(sql, new PlatMapper());

		LOGGER.info("Alors les deux plats sont identiques");
		assertThat(plat).isEqualTo(platRecupere);
	}

	@Test
	void testCount() {
		LOGGER.info("Etant donne qu'on compte les entites");
		long compte = platRepository.count();

		LOGGER.info("Lorsque l'on recupere le compte via jdbc");
		String sql = "SELECT count(*) FROM plats";
		long compteRecupere = jdbcTemplate.queryForObject(sql, long.class);

		LOGGER.info("Alors les deux comptes doivent etre egaux");
		assertThat(compte).isEqualTo(compteRecupere);

	}

	@Test
	void testFindByPrix() {
		LOGGER.info("Etant donne qu'on recupere les plats a partir du repository (prix>1300)");
		List<Plat> listePlats = platRepository.findByPrixEnCentimesEuros(1300);

		LOGGER.info("Lorsque l'on recupere les plats via jdbc (prix>1300)");
		String sql = "SELECT * FROM plats WHERE prixEnCentimesEuros=1300";
		List<Plat> listePlatsRecuperes = jdbcTemplate.query(sql, new PlatMapper());

		LOGGER.info("Alors les deux listes recuperees sont identiques");
		assertThat(listePlats).isEqualTo(listePlatsRecuperes);
	}

	@Test
	void testAvgPrix() {
		LOGGER.info("Etant donne qu'on calcule les moyenne des prix des entites au dessus d'un prix donne");
		double moyenne = platRepository.avgPrix(1300);

		LOGGER.info("Lorsque l'on recupere la meme moyenne via jdbc");
		String sql = "SELECT avg(prixEnCentimesEuros) FROM plats WHERE prixencentimeseuros > ?";
		long moyenneRecupere = jdbcTemplate.queryForObject(sql, new Object[] { "1300" }, long.class);

		LOGGER.info("Alors les deux moyennes doivent etre egales");
		assertThat(moyenne).isEqualTo(moyenneRecupere);
	}

	@Test
	@Transactional
	void testFindByNomWithIngredients() {
		LOGGER.info("Etant donne qu'on recupere le plat Moules-frites a partir du repository");
		List<Plat> plats = platRepository.findByNomWithIngredients("Moules-frites");

		LOGGER.info("Alors le plat contient 6 ingredients");
		assertThat(plats.get(0).getIngredients().size()).isEqualTo(6);
	}

	@Test
	void testSave() {
		LOGGER.info("Etant donne qu'on cree un nouveau plat");
		String nom = "Plat1";
		Plat plat = new Plat(nom, 1500);

		LOGGER.info("Lorsque l'on ajoute ce plat a la bdd");
		platRepository.save(plat);

		LOGGER.info("Alors on le retrouve bien via jdbc");
		String sql = "SELECT * FROM plats WHERE nom = ?";
		Plat platRecupere = jdbcTemplate.queryForObject(sql, new Object[] { "Plat1" }, new PlatMapper());
		assertThat(plat).isEqualTo(platRecupere);
	}

	@Test
	@Transactional
	void testChangerNom() {
		LOGGER.info("Etant donne qu'on insere un plat2");
		String nom = "Plat2";
		Plat plat = new Plat(nom, 1500);
		platRepository.save(plat);

		LOGGER.info("Lorsque l'on modifie son nom en plat3");
		platRepository.changeNom("Plat2", "Plat3");

		LOGGER.info("Alors on le retrouve bien via jdbc");
		String sql = "SELECT * FROM plats WHERE nom = ?";
		Plat platRecupere = jdbcTemplate.queryForObject(sql, new Object[] { "Plat3" }, new PlatMapper());
		assertThat(platRecupere.getNom()).isEqualTo("Plat3");
	}

}

package dev.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import dev.entite.Plat;
import dev.mapper.PlatMapper;

/**
 * @author antoinethebault PlatDaoJdbc : acces aux donnees de plat via Base De
 *         Donnees
 */
@Repository
@Profile("jdbc")
public class PlatDaoJdbc implements IPlatDao {

	private JdbcTemplate jdbcTemplate;

	public PlatDaoJdbc(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Plat> listerPlats() {
		String sql = "SELECT * FROM plats";
		List<Plat> plats = jdbcTemplate.query(sql, new PlatMapper());
		return plats;
	}

	@Override
	public void ajouterPlat(String nomPlat, Integer prixPlat) {
		String sql = "INSERT INTO plats (nom,prixencentimeseuros) VALUES (?,?)";
		jdbcTemplate.update(sql, nomPlat, prixPlat);

	}

}

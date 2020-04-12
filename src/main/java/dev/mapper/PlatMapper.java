package dev.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import dev.entite.Plat;

public class PlatMapper implements RowMapper<Plat> {

	@Override
	public Plat mapRow(ResultSet rs, int rowNum) throws SQLException {
		Plat plat = new Plat();
		plat.setNom(rs.getString("nom"));
		plat.setPrixEnCentimesEuros(rs.getInt("prixencentimeseuros"));
		return plat;
	}

}

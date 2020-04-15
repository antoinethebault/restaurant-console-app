package dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.entite.Plat;

public interface PlatRepository extends JpaRepository<Plat, Integer> {

	List<Plat> findByPrixEnCentimesEuros(int prixEnCentimesEuros);

	@Query("SELECT avg(prixEnCentimesEuros) FROM plats WHERE prixencentimeseuros > :prix")
	long avgPrix(@Param("prix") int prixEnCentimesEuros);

	@Query("SELECT p FROM plats p, ingredient i WHERE p.nom = :nom")
	List<Plat> findByNomWithIngredients(@Param("nom") String nom);

	@Modifying
	@Query("UPDATE plats SET nom = :nouveauNom WHERE nom = :ancienNom")
	void changeNom(@Param("ancienNom") String ancienNom, @Param("nouveauNom") String nouveauNom);

}

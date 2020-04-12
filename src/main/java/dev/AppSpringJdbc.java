package dev;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dev.config.AppConfig;
import dev.ihm.Menu;

public class AppSpringJdbc {
	public static void main(String[] args) {
		// Création du contexte Spring à partir d'une configuration Java
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

//		PlatDaoJdbc platDaoJdbc = context.getBean(PlatDaoJdbc.class);
//		double nombre = Math.random();
//		platDaoJdbc.ajouterPlat("plat" + nombre, 1200);
//		List<Plat> listePlats = platDaoJdbc.listerPlats();
//		for (Plat plat : listePlats)
//			System.out.println(plat.getNom());

		// récupération du bean Menu
		Menu menu = context.getBean(Menu.class);
		menu.afficher();

		// fermeture du Scanner
		context.getBean(Scanner.class).close(); // fermeture du contexte Spring

		context.close();
	}
}

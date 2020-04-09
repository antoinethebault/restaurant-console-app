package dev.ihm;

import dev.exception.PlatException;
import dev.ihm.options.IOptionMenu;
import dev.ihm.options.OptionAjouterPlat;
import dev.ihm.options.OptionListerPlats;
import dev.ihm.options.OptionTerminer;
import dev.service.IPlatService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
public class Menu {

    private Map<Integer, IOptionMenu> actions = new HashMap<>();

    private String menu;
    private Scanner scanner;
    AnnotationConfigApplicationContext context;

//    public Menu(Scanner scanner, IPlatService service) {
//        actions.put(1, new OptionListerPlats(service));
//        //actions.put(1, context.getBean("optionListerPlats",IOptionMenu.class));
//        actions.put(2, new OptionAjouterPlat(scanner, service));
//        //actions.put(2, context.getBean("optionAjouterPlats",IOptionMenu.class));
//        actions.put(99, new OptionTerminer());
//        //actions.put(99, context.getBean("optionTerminer",IOptionMenu.class));
//        this.scanner = scanner;
//    }
    
    
    public Menu(Scanner scanner, IPlatService service, AnnotationConfigApplicationContext context) {
        this.context = context;
        Map<String, OptionListerPlats> map1 = context.getBeansOfType(OptionListerPlats.class);
    	if (!map1.isEmpty())
    		actions.put(1, (OptionListerPlats) map1.get("optionListerPlats"));
    	Map<String, OptionAjouterPlat> map2 = context.getBeansOfType(OptionAjouterPlat.class);
    	if (!map2.isEmpty())
    		actions.put(2, (OptionAjouterPlat) map2.get("optionAjouterPlat"));
    	Map<String, OptionTerminer> map3  = context.getBeansOfType(OptionTerminer.class);
    	if (!map3.isEmpty())
    		actions.put(99, (OptionTerminer) map3.get("optionTerminer"));
        this.scanner = scanner;
    }
    
    public void afficher() {
    	
        boolean continuer = true;

        while (continuer) {

            System.out.println(getMenuTexte());

            int choix = this.scanner.nextInt();

            try {
                this.actions.get(choix).executer();
            } catch (PlatException e) {
                continuer = false;
                System.out.println(e.getMessage());
            }
        }
    }

    private String getMenuTexte() {
        if (menu == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("** Restaurant Console App **");
            sb.append("\n");
            this.actions.forEach((index, option) -> {
                sb.append(index);
                sb.append(". ");
                sb.append(option.getTitre());
                sb.append("\n");
            });
            this.menu = sb.toString();
        }
        return this.menu;
    }
}

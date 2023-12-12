package org.java.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.java.spring.db.serv.IngredientService;
import org.java.spring.db.serv.OfferService;
import org.java.spring.db.serv.PizzaService;
import org.java.spring.db.pojo.Ingredient;
import org.java.spring.db.pojo.Pizza;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {

	@Autowired
	private PizzaService pizzaService;

	@Autowired
	private OfferService offerService;

	@Autowired
	private IngredientService ingredientService;;

	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Ingredient ing1 = new Ingredient("olive");
		Ingredient ing2 = new Ingredient("salamino");
		Ingredient ing3 = new Ingredient("rucola");
		Ingredient ing4 = new Ingredient("salsiccia");
		Ingredient ing5 = new Ingredient("cipolla");

		ingredientService.save(ing1);
		ingredientService.save(ing2);
		ingredientService.save(ing3);
		ingredientService.save(ing4);
		ingredientService.save(ing5);

		pizzaService.save(new Pizza("Margherita", "La classica", "imageUrl_1", 700, ing1, ing3));
		pizzaService.save(new Pizza("Salamino", "La piccante", "imageUrl_2", 850, ing2));
		pizzaService.save(new Pizza("Napoli", "La salata", "imageUrl_3", 800));
		pizzaService.save(new Pizza("Calzone", "Il farcito", "imageUrl_4", 900, ing4, ing5));
		pizzaService.save(new Pizza("SuperPizza", "La speciale", "imageUrl_5", 1250, ing1, ing3, ing5));

		// List<Pizza> pizzas = pizzaService.findAll();
		// offerService.save(new Offer("Offerta 1", LocalDate.now().minusDays(10),
		// LocalDate.now(), pizzas.get(0)));
		// offerService.save(
		// new Offer("Offerta 2", LocalDate.now().minusDays(10),
		// LocalDate.now().minusDays(5), pizzas.get(1)));
		// offerService.save(new Offer("Offerta 3", LocalDate.now().minusDays(5),
		// LocalDate.now(), pizzas.get(2)));
	}
}

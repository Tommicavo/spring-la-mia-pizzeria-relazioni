package org.java.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.java.spring.db.serv.PizzaService;
import org.java.spring.db.pojo.Pizza;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {

	@Autowired
	private PizzaService pizzaService;

	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		pizzaService.save(new Pizza("Margherita", "La classica", "imageUrl_1", 700));
		pizzaService.save(new Pizza("Salamino", "La piccante", "imageUrl_2", 850));
		pizzaService.save(new Pizza("Napoli", "La salata", "imageUrl_3", 800));
		pizzaService.save(new Pizza("Calzone", "Il farcito", "imageUrl_4", 900));
		pizzaService.save(new Pizza("SuperPizza", "La speciale", "imageUrl_5", 1250));
	}
}

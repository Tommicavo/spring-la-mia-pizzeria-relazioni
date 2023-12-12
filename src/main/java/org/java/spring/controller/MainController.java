package org.java.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.java.spring.db.serv.IngredientService;
import org.java.spring.db.serv.PizzaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import java.util.List;

import org.java.spring.db.pojo.Ingredient;
import org.java.spring.db.pojo.Pizza;

@Controller
public class MainController {

	@Autowired
	private PizzaService pizzaService;

	@Autowired
	private IngredientService ingredientService;

	@GetMapping("/")
	public String index(Model model, @RequestParam(required = false) String searchedWord) {

		List<Pizza> pizzas;
		if (searchedWord == null || searchedWord.length() == 0) {
			pizzas = pizzaService.findAllWithoutTrashed();
		} else {
			pizzas = pizzaService.searchPizza(searchedWord);
			model.addAttribute("searchedWord", searchedWord);
		}

		model.addAttribute("pizzas", pizzas);
		return "pizzas";
	}

	@GetMapping("/pizzas/{id}")
	public String showPizza(Model model, @PathVariable int id) {

		Pizza pizza = pizzaService.findById(id);
		model.addAttribute("pizza", pizza);
		return "pizza";
	}

	@GetMapping("/pizzas/create")
	public String createPizza(Model model) {

		Pizza pizza = new Pizza();
		List<Ingredient> ingredients = ingredientService.findAll();

		ingredients.forEach(ingredient -> {
			ingredient.setChecked(pizza.getIngredients() != null && pizza.getIngredients().contains(ingredient));
		});

		model.addAttribute("pizza", pizza);
		model.addAttribute("ingredients", ingredients);
		model.addAttribute("create", true);

		return "formPizza";
	}

	@PostMapping("/pizzas/create")
	public String storePizza(
			Model model,
			@Valid @ModelAttribute Pizza pizza,
			BindingResult bindingResult) {

		return savePizza(model, bindingResult, pizza);
	}

	@GetMapping("/pizzas/edit/{id}")
	public String editPizza(Model model, @PathVariable int id) {

		Pizza pizza = pizzaService.findById(id);
		List<Ingredient> ingredients = ingredientService.findAll();

		model.addAttribute("pizza", pizza);
		model.addAttribute("ingredients", ingredients);
		model.addAttribute("create", false);

		return "formPizza";
	}

	@PostMapping("/pizzas/edit/{id}")
	public String updatePizza(Model model,
			@Valid @ModelAttribute Pizza pizza,
			BindingResult bindingResult) {

		return savePizza(model, bindingResult, pizza);
	}

	public String savePizza(Model model, BindingResult bindingResult, Pizza pizza) {
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			model.addAttribute("pizza", pizza);
			return "formPizza";
		}

		try {
			pizzaService.save(pizza);
		} catch (Exception e) {
			System.out.println("Create Error: " + e.getMessage());
			return "formPizza";
		}
		return "redirect:/";
	}

	@PostMapping("/pizzas/delete/{id}")
	public String deletePizza(@PathVariable int id) {

		Pizza pizza = pizzaService.findById(id);
		pizza.clearIngredients();
		pizzaService.save(pizza);

		pizzaService.delete(pizza);

		return "redirect:/";
	}

	@GetMapping("/pizzas/trash")
	public String trash(Model model) {

		List<Pizza> pizzas = pizzaService.findAllWithTrashed();
		model.addAttribute("pizzas", pizzas);

		return "trash";
	}

	@PostMapping("/pizzas/trash/{id}")
	public String trashPizza(@PathVariable int id) {
		Pizza pizza = pizzaService.findById(id);
		pizza.setDeleted(true);
		pizzaService.save(pizza);
		return "redirect:/";
	}

	@PostMapping("/pizzas/restore/{id}")
	public String restorePizza(@PathVariable int id) {
		Pizza pizza = pizzaService.findById(id);
		pizza.setDeleted(false);
		pizzaService.save(pizza);
		return "redirect:/";
	}
}

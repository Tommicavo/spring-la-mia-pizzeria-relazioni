package org.java.spring.controller;

import java.util.List;
import org.java.spring.db.pojo.Ingredient;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.IngredientService;
import org.java.spring.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
public class IngredientController<Ingredient> {

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private PizzaService pizzaService;

    @GetMapping("/ingredients")
    public String index(Model model) {
        List<org.java.spring.db.pojo.Ingredient> ingredients = ingredientService.findAll();

        model.addAttribute("ingredients", ingredients);
        model.addAttribute("ingredient", new org.java.spring.db.pojo.Ingredient());

        return "ingredients";
    }

    @PostMapping("/ingredients/store")
    public String storeIngredient(
            Model model,
            @Valid @ModelAttribute org.java.spring.db.pojo.Ingredient ingredient,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            model.addAttribute("ingredient", ingredient);
            return "ingredients";
        }

        try {
            ingredientService.save(ingredient);
        } catch (Exception e) {
            System.out.println("Create Error: " + e.getMessage());
            return "ingredients";
        }
        return "redirect:/ingredients";
    }

    @Transactional
    @PostMapping("/ingredients/delete/{id}")
    public String deleteIngredient(@PathVariable int id) {

        org.java.spring.db.pojo.Ingredient ingredient = ingredientService.findById(id);

        List<Pizza> pizzas = pizzaService.getPizzasByIngredient(ingredient);

        for (Pizza pizza : pizzas) {
            pizza.getIngredients().remove(ingredient);
            pizzaService.save(pizza);
        }

        ingredient.clearPizzas();
        ingredientService.delete(ingredient);

        return "redirect:/ingredients";
    }
}

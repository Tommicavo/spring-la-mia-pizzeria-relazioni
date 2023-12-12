package org.java.spring.controller;

import java.util.ArrayList;
import java.util.List;
import org.java.spring.db.pojo.Offer;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.OfferService;
import org.java.spring.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class OfferController {

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private OfferService offerService;

    @GetMapping("/pizzas/{id}/offers/create")
    public String index(Model model, @PathVariable int id) {
        Pizza pizza = pizzaService.findById(id);
        model.addAttribute("offer", new Offer());
        model.addAttribute("pizza", pizza);
        return "pizza-offer";
    }

    @PostMapping("/pizzas/{id}/offers/create")
    public String storeOffer(
            Model model,
            @PathVariable int id,
            @Valid @ModelAttribute Offer offer,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("offer", offer);
            return "pizza-offer";
        }

        Pizza pizza = pizzaService.findById(id);
        Offer newOffer = new Offer(offer.getTitle(), offer.getStartDate(), offer.getStopDate(), pizza);

        offer.setPizza(pizza);
        offerService.save(newOffer);

        return "redirect:/pizzas/" + id;
    }
}

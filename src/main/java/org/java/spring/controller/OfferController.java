package org.java.spring.controller;

import java.time.LocalDate;
import java.util.List;

import org.java.spring.db.dto.PizzaOfferDTO;
import org.java.spring.db.pojo.Offer;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.OfferService;
import org.java.spring.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OfferController {

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private OfferService offerService;

    @GetMapping("/pizzas/offer/{id}")
    public String index(Model model, @PathVariable int id) {
        List<Offer> offers = offerService.findAll();
        Pizza pizza = pizzaService.findById(id);
        model.addAttribute("offers", offers);
        model.addAttribute("pizza", pizza);

        return "pizza-offer";
    }

    @PostMapping("/pizzas/offer/create")
    public String storeOffer(@ModelAttribute PizzaOfferDTO pizzaOfferDTO) {

        int pizza_id = pizzaOfferDTO.getPizza_id();
        Pizza pizza = pizzaService.findById(pizza_id);

        String title = pizzaOfferDTO.getTitle();

        Offer offer = new Offer(title, LocalDate.now(), LocalDate.now().plusDays(5), pizza);
        offerService.save(offer);

        return "redirect:/";
    }
}

package org.java.spring.db.serv;

import org.java.spring.db.pojo.Ingredient;
import org.java.spring.db.repo.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

import java.util.List;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    public Ingredient findById(int id) {
        return ingredientRepository.findById(id).get();
    }

    public void save(@Valid Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    public void delete(Ingredient ingredient) {
        ingredientRepository.delete(ingredient);
    }
}

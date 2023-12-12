package org.java.spring.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.java.spring.db.pojo.Ingredient;
import org.java.spring.db.pojo.Pizza;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

	List<Pizza> findPizzaByIsDeletedFalseAndNameContainingIgnoreCase(String query);

	List<Pizza> findByIsDeletedFalse();

	List<Pizza> findPizzaByIsDeletedTrue();

	List<Pizza> findPizzasByIngredientsContaining(@Param("ingredient") Ingredient ingredient);
}

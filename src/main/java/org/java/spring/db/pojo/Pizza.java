package org.java.spring.db.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.List;
import java.util.Arrays;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;

@Entity
public class Pizza {

	// Properties
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true, nullable = false, length = 64)
	@Length(min = 1, max = 64, message = "'Name' field must be between 1 and 64 characters.")
	private String name;

	@Column(columnDefinition = "TEXT")
	private String description;

	private String imageUrl;

	@Column(nullable = false)
	@Min(value = 1, message = "'Price' field must be greater than or equals to 0.")
	private int price;

	private boolean isDeleted;

	@OneToMany
	private List<Pizza> pizzas;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Ingredient> ingredients;

	// Constructors
	public Pizza() {
		setDeleted(false);
	}

	public Pizza(String name, String description, String imageUrl, int price, Ingredient... ingredients) {
		setName(name);
		setDescription(description);
		setImageUrl(imageUrl);
		setPrice(price);
		setDeleted(false);
		setIngredients(ingredients);
	}

	// Getters & Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public void setIngredients(Ingredient... ingredients) {
		setIngredients(Arrays.asList(ingredients));
	}

	public void clearIngredients() {
		getIngredients().clear();
	}

	// Methods
	public String getFormattedPrice() {
		double doublePrice = (getPrice() / 100.0);
		String formattedPrice = String.format("%.2f", doublePrice);
		return formattedPrice + "€";
	}

	@Override
	public String toString() {
		return "[" + getId() + "] - " + getName() + " - " + getFormattedPrice();
	}
}

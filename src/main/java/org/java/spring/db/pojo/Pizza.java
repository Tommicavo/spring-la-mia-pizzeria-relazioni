package org.java.spring.db.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.persistence.GeneratedValue;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;

@Entity
public class Pizza {

	// Properties
	@Id
	@GeneratedValue
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

	// Constructors
	public Pizza() {
		setDeleted(false);
	}

	public Pizza(String name, String description, String imageUrl, int price) {
		setName(name);
		setDescription(description);
		setImageUrl(imageUrl);
		setPrice(price);
		setDeleted(false);
	}

	// Getters and Setters
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

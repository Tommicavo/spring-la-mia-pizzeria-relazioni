package org.java.spring.db.dto;

public class PizzaOfferDTO {

    private int pizza_id;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPizza_id() {
        return pizza_id;
    }

    public void setPizza_id(int pizza_id) {
        this.pizza_id = pizza_id;
    }

    @Override
    public String toString() {
        return "Title: " + getTitle() + '\n' +
                "Pizza id: " + getPizza_id();
    }
}

package org.java.spring.db.pojo;

import java.time.LocalDate;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Offer {

    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 64)
    @Length(min = 1, max = 64, message = "'Title' field must be between 1 and 64 characters.")
    private String title;

    private LocalDate startDate;

    private LocalDate stopDate;

    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;

    // Constructors
    public Offer() {
    }

    public Offer(String title, LocalDate startDate, LocalDate stopDate, Pizza pizza) {
        setTitle(title);
        setStartDate(startDate);
        setStopDate(stopDate);
        setPizza(pizza);
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getStopDate() {
        return stopDate;
    }

    public void setStopDate(LocalDate stopDate) {
        this.stopDate = stopDate;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String toString() {
        return getTitle() + " (" + getStartDate() + " - " + getStopDate() + ')';
    }
}

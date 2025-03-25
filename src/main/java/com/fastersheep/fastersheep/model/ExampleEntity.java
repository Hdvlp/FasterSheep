package com.fastersheep.fastersheep.model;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "example_entity")
public class ExampleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "currencies", nullable = false)
    private int currencies;

    @Transient // Exclude from the database; handle it manually
    private Currencies currencyObj;

    @Column(name = "timestamp", columnDefinition = "DATETIME")
    private LocalDateTime timestamp;

    @Column(nullable = false, precision = 25, scale = 10) // Supports large monetary values with up to 4 decimal places
    private BigDecimal price;


    @PostLoad
    public void populateRole() {
        this.currencyObj = Currencies.fromValue(this.currencies);
    }

    @PrePersist
    public void populateRoleValue() {
        this.currencies = this.currencyObj.getValue();
    }

    public ExampleEntity() {}

    public ExampleEntity(Currencies currencyObj, BigDecimal price, LocalDateTime timestamp) {
        this.currencyObj = currencyObj;
        this.currencies = Currencies.getNumberFromString(currencyObj.name());
        this.price = price;
        this.timestamp = timestamp;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Currencies getCurrencyObj() {
        return currencyObj;
    }

    public void setCurrencyObj(Currencies currencyObj) {
        this.currencyObj = currencyObj;
    }

    public void setCurrencies(Currencies currencyObj) {
        this.currencies = Currencies.getNumberFromString(currencyObj.name());
    }
    
    public String toString(){
        return this.getTimestamp() + " " + this.getPrice() + " " + this.getCurrencyObj().name();
    }

    public static String sanitizeTrailingZero(BigDecimal bd){
        String s = bd.toString();
        s = s.replaceAll("0+$", "");
        return s;
    }

    public String toHtml(){
        return "<div><div>" + this.getTimestamp() + 
        "</div><div>" + sanitizeTrailingZero(this.getPrice()) + 
        "</div><div>" + this.getCurrencyObj().name() +
        "</div></div>";
    }
}

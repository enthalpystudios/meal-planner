package com.enthalpystudios.domain;

import javax.persistence.*;

@Entity
public class DishVariation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Dish dish;

    @Column
    private DishVariationType type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public DishVariationType getType() {
        return type;
    }

    public void setType(DishVariationType type) {
        this.type = type;
    }
}

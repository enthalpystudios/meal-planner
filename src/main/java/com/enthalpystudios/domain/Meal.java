package com.enthalpystudios.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private MealType type;

    @OneToMany
    private List<DishVariation> dishVariations; //TODO @ManyToMany

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MealType getType() {
        return type;
    }

    public void setType(MealType type) {
        this.type = type;
    }

    public List<DishVariation> getDishVariations() {
        return dishVariations;
    }

    public void setDishVariations(List<DishVariation> dishVariations) {
        this.dishVariations = dishVariations;
    }
}

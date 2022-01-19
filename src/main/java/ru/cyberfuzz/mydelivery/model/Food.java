package ru.cyberfuzz.mydelivery.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

/**
 * Класс Food
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Embeddable
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Food {

    @Column(name = "food_id")
    private int id;
    private String name;
    private int price;
    private int amount;
    private int sum;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return id == food.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

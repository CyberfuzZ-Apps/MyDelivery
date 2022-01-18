package ru.cyberfuzz.mydelivery.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Класс Order
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Order {

    private int id;
    private String username;
    private String address;
    private Timestamp created;
    private int orderSum;

    private Collection<Food> foods = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

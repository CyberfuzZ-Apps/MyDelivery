package ru.cyberfuzz.mydelivery.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

/**
 * Класс Courier
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Courier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String phone;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Courier courier = (Courier) o;
        return id == courier.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

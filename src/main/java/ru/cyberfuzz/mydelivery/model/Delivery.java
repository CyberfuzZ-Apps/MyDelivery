package ru.cyberfuzz.mydelivery.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;
import ru.cyberfuzz.mydelivery.status.DeliveryStatus;

import javax.persistence.*;
import java.util.Objects;

/**
 * Класс Delivery
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Delivery {

    private int id;
    private String courierName;
    private DeliveryStatus status;

    private Order order;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Delivery delivery = (Delivery) o;
        return id == delivery.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

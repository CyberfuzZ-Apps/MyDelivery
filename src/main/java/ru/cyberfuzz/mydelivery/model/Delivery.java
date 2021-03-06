package ru.cyberfuzz.mydelivery.model;

import lombok.*;
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
@Component
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Transient
    private DeliveryStatus status;

    @ManyToOne
    private Courier courier;

    @Embedded
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

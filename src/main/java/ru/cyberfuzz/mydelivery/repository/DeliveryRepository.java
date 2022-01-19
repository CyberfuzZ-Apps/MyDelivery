package ru.cyberfuzz.mydelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cyberfuzz.mydelivery.model.Delivery;

/**
 * Класс DeliveryRepository
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
}

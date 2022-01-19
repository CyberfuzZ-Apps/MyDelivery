package ru.cyberfuzz.mydelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.cyberfuzz.mydelivery.model.Courier;

/**
 * Класс CourierRepository
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public interface CourierRepository extends JpaRepository<Courier, Integer> {

    @Query(nativeQuery = true, value =
            "SELECT * FROM courier ORDER BY random() LIMIT 1")
    Courier findRandomCourier();
}

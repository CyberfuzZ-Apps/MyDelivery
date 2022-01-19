package ru.cyberfuzz.mydelivery.service;

import org.springframework.stereotype.Service;
import ru.cyberfuzz.mydelivery.model.Courier;
import ru.cyberfuzz.mydelivery.repository.CourierRepository;

/**
 * Класс CourierService
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Service
public class CourierService {

    private final CourierRepository courierRepository;

    public CourierService(CourierRepository courierRepository) {
        this.courierRepository = courierRepository;
    }

    public Courier findRandomCourier() {
        return courierRepository.findRandomCourier();
    }
}

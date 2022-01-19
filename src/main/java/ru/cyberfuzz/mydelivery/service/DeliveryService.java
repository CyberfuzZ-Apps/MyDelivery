package ru.cyberfuzz.mydelivery.service;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cyberfuzz.mydelivery.model.Courier;
import ru.cyberfuzz.mydelivery.model.Delivery;
import ru.cyberfuzz.mydelivery.model.Order;
import ru.cyberfuzz.mydelivery.repository.DeliveryRepository;
import ru.cyberfuzz.mydelivery.status.DeliveryStatus;

/**
 * Класс DeliveryService
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Service
public class DeliveryService {

    private final ApplicationContext applicationContext;
    private final CourierService courierService;
    private final DeliveryRepository deliveryRepository;

    public DeliveryService(ApplicationContext applicationContext,
                           CourierService courierService,
                           DeliveryRepository deliveryRepository) {
        this.applicationContext = applicationContext;
        this.courierService = courierService;
        this.deliveryRepository = deliveryRepository;
    }

    @Transactional
    public Delivery saveDelivery(Order order) {
        Delivery delivery = applicationContext.getBean(Delivery.class);
        if (delivery.getId() != 0) {
            delivery.setId(0);
        }
        Courier courier = courierService.findRandomCourier();
        delivery.setOrder(order);
        delivery.setCourier(courier);
        return deliveryRepository.save(delivery);
    }
}

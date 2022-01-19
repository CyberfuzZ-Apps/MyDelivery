package ru.cyberfuzz.mydelivery.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.cyberfuzz.mydelivery.model.Courier;
import ru.cyberfuzz.mydelivery.model.Delivery;
import ru.cyberfuzz.mydelivery.model.Order;
import ru.cyberfuzz.mydelivery.service.CourierService;
import ru.cyberfuzz.mydelivery.service.DeliveryService;
import ru.cyberfuzz.mydelivery.status.DeliveryStatus;

/**
 * Класс DeliveryController
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Component
public class DeliveryController {

    private final ObjectMapper objectMapper;
    private final DeliveryService deliveryService;

    public DeliveryController(ObjectMapper objectMapper,
                              DeliveryService deliveryService) {
        this.objectMapper = objectMapper;
        this.deliveryService = deliveryService;
    }

    @KafkaListener(topics = {"order"})
    public void listenOrder(ConsumerRecord<Integer, String> input) {
        try {
            Order order = objectMapper.readValue(input.value(), Order.class);
            Delivery delivery = deliveryService.saveDelivery(order);
            delivery.setStatus(DeliveryStatus.ПРИНЯТО);
            System.out.println(delivery);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

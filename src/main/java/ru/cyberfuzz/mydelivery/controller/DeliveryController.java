package ru.cyberfuzz.mydelivery.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.cyberfuzz.mydelivery.model.Order;

/**
 * Класс DeliveryController
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Component
public class DeliveryController {

    @KafkaListener(topics = {"order"})
    public void listenOrder(ConsumerRecord<Integer, String> input) {
        System.out.println("===============");
        System.out.println(input.value());
    }
}

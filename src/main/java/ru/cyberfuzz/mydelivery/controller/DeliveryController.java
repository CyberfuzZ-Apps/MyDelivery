package ru.cyberfuzz.mydelivery.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import ru.cyberfuzz.mydelivery.model.Delivery;
import ru.cyberfuzz.mydelivery.model.Order;
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
    private final KafkaTemplate<Integer, Delivery> kafkaTemplate;

    public DeliveryController(ObjectMapper objectMapper,
                              DeliveryService deliveryService,
                              KafkaTemplate<Integer, Delivery> kafkaTemplate) {
        this.objectMapper = objectMapper;
        this.deliveryService = deliveryService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = {"order"})
    public void listenOrder(ConsumerRecord<Integer, String> input) {
        try {
            Order order = objectMapper.readValue(input.value(), Order.class);
            Delivery delivery = deliveryService.saveDelivery(order);
            delivery.setStatus(DeliveryStatus.ПРИНЯТО);
            System.out.println(delivery);
            System.out.println("Отсылаем сообщение!!!");
            sendDelivery(delivery);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void sendDelivery(Delivery delivery) {
        try {
            String topic = "delivery";
            kafkaTemplate.send(topic, delivery);
            Thread.sleep(5000);
            delivery.setStatus(DeliveryStatus.ОБРАБАТЫВАЕТСЯ);
            kafkaTemplate.send(topic, delivery);
            Thread.sleep(5000);
            delivery.setStatus(DeliveryStatus.В_ПУТИ);
            kafkaTemplate.send(topic, delivery);
            Thread.sleep(5000);
            delivery.setStatus(DeliveryStatus.ДОСТАВЛЕНО);
            kafkaTemplate.send(topic, delivery);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

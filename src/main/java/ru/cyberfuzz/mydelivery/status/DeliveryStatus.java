package ru.cyberfuzz.mydelivery.status;

import javax.persistence.Embeddable;

/**
 * Класс DeliveryStatus
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public enum DeliveryStatus {
    ПРИНЯТО,
    ОБРАБАТЫВАЕТСЯ,
    В_ПУТИ,
    ДОСТАВЛЕНО,
    ОТМЕНЕН
}

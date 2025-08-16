package com.vitasync.notification_service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.vitasync.shared.events.TransfusionRequestedEvent;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "transfusion-requests-topic", groupId = "notification-service-group")
    public void listenTransfusionRequests(TransfusionRequestedEvent event) {
        System.out.println("Received event: " + event);

        // This is where you would add logic to send a notification (e.g., via a WhatsApp API).
        // For now, just a print statement is enough to confirm it's working.
    }
}
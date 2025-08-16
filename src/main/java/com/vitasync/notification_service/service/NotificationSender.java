package com.vitasync.notification_service.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.vitasync.shared.events.TransfusionRequestedEvent;

@Service
public class NotificationSender {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    // A hardcoded recipient for testing purposes.
    // In a real application, you would get this from another service (e.g., a user service)
    private String recipientPhoneNumber = "+1234567890"; // Replace with your phone number

    public void sendNotification(TransfusionRequestedEvent event) {
        Twilio.init(accountSid, authToken);

        String messageBody = String.format(
            "VitaSync: A transfusion request has been made for blood type %s at %s. Request ID: %d",
            event.bloodType(),
            event.location(),
            event.requestId()
        );

        Message message = Message.creator(
            new com.twilio.type.PhoneNumber(recipientPhoneNumber),
            new com.twilio.type.PhoneNumber(twilioPhoneNumber),
            messageBody
        ).create();

        System.out.println("Notification sent successfully! SID: " + message.getSid());
    }
}
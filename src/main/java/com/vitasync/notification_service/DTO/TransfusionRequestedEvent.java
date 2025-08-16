package com.vitasync.notification_service.DTO;

public record TransfusionRequestedEvent(
    Long requestId,
    Long patientId,
    String bloodType,
    String location,
    String status
) {    
}
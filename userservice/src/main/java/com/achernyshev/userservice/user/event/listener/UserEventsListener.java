package com.achernyshev.userservice.user.event.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserEventsListener {

    @KafkaListener(topics = "user-events", groupId = "workflow-service")
    public void listen(String email) {
        log.info("Received UserRegistered event for email: {}", email);
    }
}

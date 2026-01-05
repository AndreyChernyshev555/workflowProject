package com.achernyshev.userservice.user.event.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.achernyshev.userservice.user.event.producer.UserEventProducer;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserEventController {

    private final UserEventProducer producer;

    @PostMapping("/users/register")
    public String register(@RequestParam String email) {
        producer.publishUserRegistered(email);
        return "Sent";
    }
}

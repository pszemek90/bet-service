package com.prezcode.betservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bets")
public class BetController {

    @GetMapping("/health")
    public Mono<ResponseEntity<String>> healthcheck() {
        return Mono.just(ResponseEntity.ok("Health is fine"));
    }
}

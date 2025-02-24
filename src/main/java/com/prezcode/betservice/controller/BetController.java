package com.prezcode.betservice.controller;

import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bets")
public class BetController {

    @GetMapping("/health")
    public Mono<ResponseEntity<String>> healthcheck(@RequestHeader("X-correlation-id") String correlationId) {
        MDC.put("correlationId", correlationId);
        return Mono.just(ResponseEntity.ok("Health is fine"));
    }
}

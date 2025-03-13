package com.prezcode.betservice.controller;

import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/bets")
public class BetController {

    private List<byte[]> memoryLeakList = new ArrayList<>();

    @GetMapping("/health")
    public Mono<ResponseEntity<String>> healthcheck(@RequestHeader("X-correlation-id") String correlationId) {
        MDC.put("correlationId", correlationId);
        return Mono.just(ResponseEntity.ok("Health is fine"));
    }

    @GetMapping("/cpu")
    public Mono<Void> cpuIntensiveTask() {
        IntStream.range(0, 10_000).parallel().forEach(i -> {
            double result = Math.tan(Math.random());
        });
        return Mono.empty();
    }

    @GetMapping("/memory")
    public Mono<Void> memoryLeak() {
        memoryLeakList.add(new byte[128 * 1024]);
        return Mono.empty();
    }

    @GetMapping("/latency")
    public Mono<Void> randomLatency() {
        return Mono.delay(Duration.ofMillis(ThreadLocalRandom.current().nextLong(0,5000))).then();
    }
}

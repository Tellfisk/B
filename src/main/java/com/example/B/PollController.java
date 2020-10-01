package com.example.B;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class PollController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/vote")
    public Vote greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Vote(counter.incrementAndGet(), String.format(template, name));
    }


}
package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huang
 */
@RestController
@SpringBootApplication
public class Module01Application {
    public static void main(String[] args) {
        SpringApplication.run(Module01Application.class, args);
    }

    @GetMapping("/echo/{str}")
    public String echo(@PathVariable String str) {
        return "Hello Discovery " + str;
    }
}

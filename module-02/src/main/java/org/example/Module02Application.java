package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author huang
 */
@SpringBootApplication
public class Module02Application {

    public static void main(String[] args) {
        SpringApplication.run(Module02Application.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RestController
    static class EchoController {
        @Resource
        private RestTemplate restTemplate;

        @GetMapping("/echo/{str}")
        public String echo(@PathVariable String str) {
            return restTemplate.getForObject("http://module-01/echo/" + str, String.class);
        }
    }
}

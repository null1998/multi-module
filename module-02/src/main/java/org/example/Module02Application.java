package org.example;

import org.example.api.Module01EchoApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
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
@EnableFeignClients
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

        @Resource
        private Module01EchoApi module01EchoApi;

        @GetMapping("/echo/{str}")
        public String echo(@PathVariable String str) {
            return restTemplate.getForObject("http://module-01/echo/" + str, String.class);
        }

        @GetMapping("/echo/feign/{str}")
        public String echoFeign(@PathVariable String str) {
            return module01EchoApi.echo(str);
        }
    }
}

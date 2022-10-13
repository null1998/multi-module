package org.example;

import org.apache.rocketmq.common.message.Message;
import org.example.producer.RocketMqProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * @author huang
 */
@RestController
@SpringBootApplication
public class Module01Application {
    @Resource
    private RocketMqProducer rocketMqProducer;

    public static void main(String[] args) {
        SpringApplication.run(Module01Application.class, args);
    }

    @GetMapping("/echo/{str}")
    public String echo(@PathVariable String str) {
        return "Hello Discovery " + str;
    }

    @GetMapping("/rocketMq/send/{key}/{content}")
    public void rocketMqSend(@PathVariable String key, @PathVariable String content) {
        Message message = new Message("testTopic", key, content.getBytes(StandardCharsets.UTF_8));
        rocketMqProducer.send(message);
    }
}

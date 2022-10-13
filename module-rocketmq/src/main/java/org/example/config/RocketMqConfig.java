package org.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @author huang
 */
@Configuration(value = "rocketMqConfig")
@ConfigurationProperties(prefix = "rocketmq")
@RefreshScope
public class RocketMqConfig {
    private String producerGroup;
    private String consumerGroup;
    private String namesrvAddr;

    public String getProducerGroup() {
        return producerGroup;
    }

    public void setProducerGroup(String producerGroup) {
        this.producerGroup = producerGroup;
    }

    public String getConsumerGroup() {
        return consumerGroup;
    }

    public void setConsumerGroup(String consumerGroup) {
        this.consumerGroup = consumerGroup;
    }

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    @Override
    public String toString() {
        return "RocketMqConfig{" +
                "producerGroup='" + producerGroup + '\'' +
                ", consumerGroup='" + consumerGroup + '\'' +
                ", namesrvAddr='" + namesrvAddr + '\'' +
                '}';
    }
}

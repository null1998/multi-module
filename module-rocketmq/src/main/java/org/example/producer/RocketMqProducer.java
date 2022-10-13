package org.example.producer;

import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.apache.commons.lang3.concurrent.LazyInitializer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.example.config.RocketMqConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author huang
 */
@Service
public class RocketMqProducer {

    @Resource(name = "rocketMqConfig")
    private final RocketMqConfig rocketMqConfig;

    private final DefaultProducerLazyInitializer defaultProducerLazyInitializer;

    public RocketMqProducer(RocketMqConfig rocketMqConfig) {
        this.rocketMqConfig = rocketMqConfig;
        this.defaultProducerLazyInitializer = new DefaultProducerLazyInitializer();
    }

    public void send(Message message) {
        DefaultMQProducer producer = getLazyDefaultProducer();
        try {
            SendResult sendResult = producer.send(message);
            if (!SendStatus.SEND_OK.equals(sendResult.getSendStatus())) {
                throw new RuntimeException("发送消息失败");
            }
        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private DefaultMQProducer getLazyDefaultProducer() {
        try {
            return defaultProducerLazyInitializer.get();
        } catch (ConcurrentException e) {
            throw new RuntimeException("获取MQ生产者失败", e);
        }
    }

    class DefaultProducerLazyInitializer extends LazyInitializer<DefaultMQProducer> {

        @Override
        protected DefaultMQProducer initialize() {
            DefaultMQProducer producer = new DefaultMQProducer(rocketMqConfig.getProducerGroup());
            producer.setNamesrvAddr(rocketMqConfig.getNamesrvAddr());
            try {
                producer.start();
            } catch (MQClientException e) {
                e.printStackTrace();
            }
            return producer;
        }
    }
}

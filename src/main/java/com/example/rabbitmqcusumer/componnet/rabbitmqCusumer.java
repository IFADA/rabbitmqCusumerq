package com.example.rabbitmqcusumer.componnet;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class rabbitmqCusumer {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "queue-1", declare = "true"),
            exchange = @Exchange(name = "exchange-1",
                    durable = "ture",
                    type = "topic",
            ignoreDeclarationExceptions = "true"),
            key = "springboot.*"
    ))
    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws Exception {
        System.out.println("------------------------------------------");
        System.out.println("消费消息"+message.getPayload());
        Long deliverTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliverTag,false);

    }
}

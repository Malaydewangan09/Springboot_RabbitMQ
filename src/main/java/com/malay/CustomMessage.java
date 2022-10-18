package com.malay;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;

public class CustomMessage {
    public static final String MESSAGE_QUEUE = "Message queue";
    public static final String EXCHANGE = "MESSAGE_EXCHANGE";
    public static final String ROUTING_KEY = "message_routing_key";

    @Bean

    private Queue queue(){



        return new Queue(MESSAGE_QUEUE);

    }
@Bean
    private Binding binding(Queue queue,TopicExchange topicExchange){
        return BindingBuilder
                .bind(queue)
                .to(topicExchange)
                .with(ROUTING_KEY);

    }
@Bean
    private TopicExchange topicExchange(){
        return new TopicExchange(EXCHANGE) ;

    }
@Bean

    public MessageConverter messageConverter(){

        return new Jackson2JsonMessageConverter();
    }

    public AmqpTemplate template(ConnectionFactory connectionFactory){
        ConnectionFactory connectionFactory1 ;
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }





}

package com.sitop.smart365.dataservice.configurer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RabbitMQConfig {
    //实时数据消息交换机名称
    public static final String REALTIME_EXCHANGE = "Smart365_realtime_exchange";
    //告警数据消息交换机名称
    public static final  String ALARM_EXCHANGE="Smart365_alarm_exchange";
    //历史数据消息交换机名称
    public static final String HISTORY_EXCHANGE="Smart365_history_exchange";
    //实时告警队列Key
    public static final String ALARM_ROUTINGKEY = "Alarm.#";
    //历史数据队列key
    public static final String HISTORY_ROUTINGKEY ="History.#";

    @Bean(name = "defaultConnectionFactory")
    @Primary
    public ConnectionFactory defaultConnectionFactory(
            @Value("${spring.rabbitmq.alarm.host}") String host,
            @Value("${spring.rabbitmq.alarm.port}") int port,
            @Value("${spring.rabbitmq.alarm.username}") String username,
            @Value("${spring.rabbitmq.alarm.password}") String password
    ) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    @Bean(name = "firstRabbitTemplate")
    @Primary
    public RabbitTemplate firstRabbitTemplate(
            @Qualifier("defaultConnectionFactory") ConnectionFactory connectionFactory
    ) {
        RabbitTemplate firstRabbitTemplate = new RabbitTemplate(connectionFactory);
        return firstRabbitTemplate;
    }


   //@Bean(name = "defaultContainerFactory")
   // public SimpleRabbitListenerContainerFactory defaultContainerFactory(
   //         SimpleRabbitListenerContainerFactoryConfigurer configurer,
   //         @Qualifier("defaultConnectionFactory") ConnectionFactory connectionFactory
   // ) {
   //     SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
   //     configurer.configure(factory, connectionFactory);
   //     return factory;
   // }

    @Bean
    public TopicExchange realtimeExchange(){
        return  new TopicExchange(REALTIME_EXCHANGE,true,false);
    }

    @Bean
    public TopicExchange alarmExchange(){
        return new TopicExchange(ALARM_EXCHANGE,true,false);
    }

    @Bean
    public TopicExchange historyExchange(){
        return  new TopicExchange(HISTORY_EXCHANGE,true,false);
    }

    @Bean
    public Queue alarmDataQueue(){
        return  new Queue("Smart365_alarm_service",true);
    }

    @Bean
    public Queue historyDataQueue(){
        return  new Queue("Smart365_history_service", true);
    }

    @Bean
    public Binding alarmBinding(){
        return BindingBuilder.bind(alarmDataQueue()).to(alarmExchange()).with(ALARM_ROUTINGKEY);
    }

    @Bean
    public Binding historyDataBinding(){
        return  BindingBuilder.bind(historyDataQueue()).to(historyExchange()).with(HISTORY_ROUTINGKEY);
    }
}

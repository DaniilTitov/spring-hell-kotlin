package ru.spring.hell.configuration


import org.springframework.amqp.core.*
import org.springframework.amqp.core.BindingBuilder.bind
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class RabbitConfiguration {

    @Bean
    fun connectionFactory(): ConnectionFactory {
        return CachingConnectionFactory("127.0.0.1")
    }

    @Bean
    fun amqpAdmin(connectionFactory: ConnectionFactory): AmqpAdmin {
        return RabbitAdmin(connectionFactory)
    }

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate {
        return RabbitTemplate(connectionFactory)
    }

    @Bean
    fun firstPersonQueue(): Queue {
        return Queue("firstPersonQueue")
    }

    @Bean
    fun secondPersonQueue(): Queue {
        return Queue("secondPersonQueue")
    }

    @Bean
    fun fanoutExchange(): FanoutExchange {
        return FanoutExchange("person-exchange")
    }

    @Bean
    fun bindingFirstQueue(firstPersonQueue: Queue, fanoutExchange: FanoutExchange): Binding {
        return bind(firstPersonQueue).to(fanoutExchange)
    }

    @Bean
    fun bindingSecondQueue(secondPersonQueue: Queue, fanoutExchange: FanoutExchange): Binding {
        return bind(secondPersonQueue).to(fanoutExchange)
    }
}
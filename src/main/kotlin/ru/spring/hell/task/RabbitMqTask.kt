package ru.spring.hell.task

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class RabbitMqTask(private val template: RabbitTemplate) {

    @Scheduled(fixedRate = 5000)
    fun sendMessage() {
        template.setExchange("person-exchange")
        template.convertSendAndReceive("Daniil")
    }

}
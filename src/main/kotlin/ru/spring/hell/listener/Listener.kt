package ru.spring.hell.listener

import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component


@Component
@EnableRabbit
class Listener {
    var logger: Logger = getLogger(Listener::class.java)

    @RabbitListener(queues = ["firstPersonQueue"])
    fun processPersonQueue(message: String) {
        logger.info("In listener first queue: $message")
    }

    @RabbitListener(queues = ["secondPersonQueue"])
    fun processPersonQueue1(message: String) {
        logger.info("In listener second queue: $message")
    }
}

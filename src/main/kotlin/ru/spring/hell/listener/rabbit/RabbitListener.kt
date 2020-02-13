package ru.spring.hell.listener.rabbit

import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component


@Component
@EnableRabbit
class RabbitListener {
    var logger: Logger = getLogger(this::class.java)

//    @RabbitListener(queues = ["firstPersonQueue"])
//    fun processPersonQueue(message: String) {
//        logger.info("In listener first queue: $message")
//    }

    @RabbitListener(queues = ["secondPersonQueue"])
    fun processPersonQueue1(message: String) {
        logger.info("In listener second queue: $message")
    }
}

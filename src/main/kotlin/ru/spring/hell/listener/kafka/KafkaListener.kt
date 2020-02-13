package ru.spring.hell.listener.kafka

import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Component
import ru.spring.hell.model.Person


@Component
class KafkaListener {
    private val log: Logger = getLogger(this::class.java)

//    @KafkaListener(topics = ["spring-hell-json"], containerFactory = "jsonKafkaListenerContainerFactory")
//    fun listen(person: Person) {
//        log.info("Received message: $person")
//    }

    @KafkaListener(topics = ["person-topic"], containerFactory = "kafkaListenerContainerFactory")
    @SendTo
    fun listen(request: Person): Person {
        log.info("IN LISTENER!!!!")
        request.firstName = "request"
        request.lastName = "reply"
        return request
    }
}

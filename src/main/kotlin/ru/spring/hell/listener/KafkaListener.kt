package ru.spring.hell.listener

import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import ru.spring.hell.model.Person


@Component
class KafkaListener {
    private val log: Logger = getLogger(this::class.java)

    @KafkaListener(topics = ["spring-hell-json"], containerFactory = "jsonKafkaListenerContainerFactory")
    fun listen(person: Person) {
        log.info("Received message: $person")
    }
}

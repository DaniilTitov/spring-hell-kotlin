package ru.spring.hell.task.kafka

import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import ru.spring.hell.model.Person
import java.util.*
import kotlin.random.Random

//@Component
//class KafkaPublishTask(private val jsonKafkaTemplate: KafkaTemplate<String, Person>) {
//    private val log: Logger = getLogger(KafkaPublishTask::class.java)
//
//    @Scheduled(fixedRate = 5000)
//    fun reportCurrentTime() {
//        val person = Person("10", "Daniil", "Titov", Random.nextInt())
//        val now = Date()
//        log.info("$now | Sending to Kafka | $person")
//        jsonKafkaTemplate.send("spring-hell-json", person)
//    }
//}

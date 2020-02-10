package ru.spring.hell.task

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class KafkaPublishTask(private val kafkaTemplate: KafkaTemplate<String, String>) {

    @Scheduled(fixedRate = 5000)
    fun reportCurrentTime() {
        kafkaTemplate.send("spring-hell", "Hello, World")
    }
}

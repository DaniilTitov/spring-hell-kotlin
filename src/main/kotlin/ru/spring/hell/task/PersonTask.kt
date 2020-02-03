package ru.spring.hell.task

import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import ru.spring.hell.client.PersonClient


@Component
class PersonTask(private val personClient: PersonClient) {
    private val log: Logger = getLogger(PersonTask::class.java)

    @Scheduled(fixedRate = 5000)
    fun reportCurrentTime() {
        val person = personClient.get("5e386aadc8f86706fe730403")
        log.info("Person: {}", person)
    }

}
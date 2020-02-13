package ru.spring.hell.task

import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.header.Header
import org.apache.kafka.common.header.internals.RecordHeader
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate
import org.springframework.kafka.requestreply.RequestReplyFuture
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.kafka.support.KafkaHeaders.*
import org.springframework.kafka.support.SendResult
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import ru.spring.hell.client.PersonClient
import ru.spring.hell.model.Person


@Component
class PersonTask(private val personClient: PersonClient, private val kafkaTemplate: ReplyingKafkaTemplate<String, Person, Person>) {
    private val log: Logger = getLogger(PersonTask::class.java)

//    @Scheduled(fixedRate = 5000)
//    fun reportCurrentTime() {
//        val person = personClient.get("5e386aadc8f86706fe730403")
//        log.info("Person: {}", person)
//    }

    @Scheduled(fixedRate = 5000)
    fun send() {
        val person = Person("01", "kek", "lol", 10)
        val record: ProducerRecord<String, Person> = ProducerRecord("person-topic", person)

        record.headers().add(RecordHeader(REPLY_TOPIC, "person-topic".toByteArray()))

        // post in kafka topic
        val sendAndReceive: RequestReplyFuture<String, Person, Person> = kafkaTemplate.sendAndReceive(record)

        // confirm if producer produced successfully
        val sendResult: SendResult<String, Person> = sendAndReceive.sendFuture.get()

        //print all headers
//        sendResult.producerRecord.headers().forEach { header: Header -> log.info(header.key() + ":" + header.value().toString()) }

        // get consumer record
        val consumerRecord: ConsumerRecord<String, Person> = sendAndReceive.get()

        // return consumer value
        val value = consumerRecord.value()
        log.info(value.toString())
    }

}
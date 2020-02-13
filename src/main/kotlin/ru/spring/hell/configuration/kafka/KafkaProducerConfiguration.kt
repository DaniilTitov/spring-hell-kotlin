package ru.spring.hell.configuration.kafka

import org.apache.kafka.clients.producer.ProducerConfig.*
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer
import ru.spring.hell.model.Person


@Configuration
class KafkaProducerConfiguration {
    @Value(value = "\${spring.kafka.bootstrap-address}")
    private lateinit var bootstrapAddress: String
//
//    @Bean
//    fun producerFactory(): ProducerFactory<String, String> {
//        val configProperties = mapOf(
//                Pair(BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress),
//                Pair(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java),
//                Pair(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java)
//        )
//        return DefaultKafkaProducerFactory(configProperties)
//    }
//
//    @Bean
//    fun kafkaTemplate(): KafkaTemplate<String, String> {
//        return KafkaTemplate(producerFactory())
//    }
//
//    @Bean
//    fun jsonProducerFactory(): ProducerFactory<String, Person> {
//        val configProperties = mapOf(
//                Pair(BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress),
//                Pair(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java),
//                Pair(VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer::class.java)
//        )
//        return DefaultKafkaProducerFactory(configProperties)
//    }
//
//    @Bean
//    fun jsonKafkaTemplate(): KafkaTemplate<String, Person> {
//        return KafkaTemplate(jsonProducerFactory())
//    }
}
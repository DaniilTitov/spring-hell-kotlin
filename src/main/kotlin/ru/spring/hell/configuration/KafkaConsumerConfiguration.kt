package ru.spring.hell.configuration

import org.apache.kafka.clients.consumer.ConsumerConfig.*
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer
import ru.spring.hell.model.Person
import java.util.*


@EnableKafka
@Configuration
class KafkaConsumerConfiguration {
    @Value(value = "\${spring.kafka.bootstrap-address}")
    private lateinit var bootstrapAddress: String

    @Value(value = "\${spring.kafka.listener.group-id}")
    private lateinit var groupId: String

    @Bean
    fun consumerFactory(): ConsumerFactory<String, String> {
        val props: MutableMap<String, Any> = HashMap()
        props[BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
        props[GROUP_ID_CONFIG] = groupId
        props[KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        return DefaultKafkaConsumerFactory(props)
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, String> {
        return ConcurrentKafkaListenerContainerFactory<String, String>().apply {
            consumerFactory = consumerFactory()
        }
    }

    @Bean
    fun jsonConsumerFactory(): ConsumerFactory<String, Person> {
        val props: MutableMap<String, Any> = HashMap()
        props[BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
        props[GROUP_ID_CONFIG] = groupId
        val jsonDeserializer = JsonDeserializer<Person>().apply {
            addTrustedPackages("*")
        }
        return DefaultKafkaConsumerFactory<String, Person>(props, StringDeserializer(), jsonDeserializer)
    }

    @Bean
    fun jsonKafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, Person> {
        return ConcurrentKafkaListenerContainerFactory<String, Person>().apply {
            consumerFactory = jsonConsumerFactory()
        }
    }

}

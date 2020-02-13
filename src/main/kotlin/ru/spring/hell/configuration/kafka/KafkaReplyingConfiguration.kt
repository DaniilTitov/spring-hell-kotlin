package ru.spring.hell.configuration.kafka


import org.apache.kafka.clients.producer.ProducerConfig.*
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.config.KafkaListenerContainerFactory
import org.springframework.kafka.core.*
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer
import org.springframework.kafka.listener.ContainerProperties
import org.springframework.kafka.listener.KafkaMessageListenerContainer
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate
import org.springframework.kafka.support.serializer.JsonSerializer
import ru.spring.hell.model.Person
import java.util.*


@Configuration
class KafkaReplyingConfiguration {

    @Value(value = "\${spring.kafka.bootstrap-address}")
    private lateinit var bootstrapAddress: String

    @Value("\${spring.kafka.topic.person.reply}")
    private lateinit var replyTopic: String

    @Value(value = "\${spring.kafka.listener.group-id}")
    private lateinit var groupId: String

    @Bean
    fun replyKafkaTemplate(container: KafkaMessageListenerContainer<String, Person>): ReplyingKafkaTemplate<String, Person, Person> {
        val replyingKafkaTemplate = ReplyingKafkaTemplate<String, Person, Person>(requestReplyProducerFactory(), container)
        replyingKafkaTemplate.setSharedReplyTopic(true)
        return replyingKafkaTemplate
    }

    @Bean
    fun replyContainer(cf: ConsumerFactory<String, Person>): KafkaMessageListenerContainer<String, Person> {
        val containerProperties = ContainerProperties("person-topic")
        return KafkaMessageListenerContainer(cf, containerProperties)
    }

    @Bean
    fun requestReplyProducerFactory(): ProducerFactory<String, Person> {
        return DefaultKafkaProducerFactory<String, Person>(producerConfigs())
    }

    @Bean
    fun producerConfigs(): Map<String, Any> {
        val props: MutableMap<String, Any> = HashMap()
        props[BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
        props[KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        props[VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        return props
    }


//    @Bean
//    fun consumerFactory(): ConsumerFactory<String, Person> {
//        return DefaultKafkaConsumerFactory<String, Person>(consumerConfigs(), StringDeserializer(), JsonDeserializer(Person::class.java))
//    }

    @Bean
    fun kafkaListenerContainerFactory(cf: ConsumerFactory<String, Person>): KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Person>> {
        val factory: ConcurrentKafkaListenerContainerFactory<String, Person> = ConcurrentKafkaListenerContainerFactory()
        factory.consumerFactory = cf
        factory.setReplyTemplate(requestReplyKafkaTemplate())
        return factory
    }

    @Bean
    fun requestReplyKafkaTemplate(): KafkaTemplate<String, Person> {
        return KafkaTemplate(requestReplyProducerFactory())
    }
}
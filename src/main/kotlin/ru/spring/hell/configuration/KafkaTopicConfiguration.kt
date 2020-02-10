package ru.spring.hell.configuration

import org.apache.kafka.clients.admin.AdminClientConfig.*
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaAdmin
import java.util.*


@Configuration
class KafkaTopicConfiguration {
    @Value(value = "\${spring.kafka.bootstrap-address}")
    private lateinit var bootstrapAddress: String

    @Bean
    fun kafkaAdmin(): KafkaAdmin {
        val configs: MutableMap<String, Any> = HashMap()
        configs[BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
//        configs[CLIENT_DNS_LOOKUP_CONFIG] = "resolve_canonical_bootstrap_servers_only"
        configs[CLIENT_ID_CONFIG] = "0"

        return KafkaAdmin(configs)
    }

    @Bean
    fun topic(): NewTopic {
        return NewTopic("spring-hell-json", 1, 1)
    }
}

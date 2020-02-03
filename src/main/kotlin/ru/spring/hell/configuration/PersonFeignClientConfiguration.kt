package ru.spring.hell.configuration

import com.google.gson.Gson
import org.springframework.context.annotation.Bean


class PersonFeignClientConfiguration {
    @Bean
    fun feignDecoder(): Gson {
        return Gson()
    }
}
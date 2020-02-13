package ru.spring.hell.configuration.feign

import feign.RequestInterceptor
import feign.codec.Decoder
import feign.gson.GsonDecoder
import org.springframework.context.annotation.Bean


class PersonFeignClientConfiguration {
    @Bean
    fun feignDecoder(): Decoder {
        return GsonDecoder()
    }

    @Bean
    fun requestInterceptor(): RequestInterceptor = RequestInterceptor { println("intercept") }
}

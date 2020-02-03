package ru.spring.hell.configuration

import feign.codec.Decoder
import feign.codec.Encoder
import feign.jaxb.JAXBContextFactory
import feign.soap.SOAPDecoder
import feign.soap.SOAPEncoder
import org.springframework.context.annotation.Bean

class ItemFeignClientConfiguration {
    @Bean
    fun jaxbContextFactory(): JAXBContextFactory {
        return JAXBContextFactory.Builder()
                .withMarshallerJAXBEncoding("UTF-8")
                .withMarshallerSchemaLocation("http://127.0.0.1:8080/ws/items.wsdl")
                .build()
    }

    @Bean
    fun feignDecoder(jaxbContextFactory: JAXBContextFactory): Decoder {
        return SOAPDecoder(jaxbContextFactory)
    }

    @Bean
    fun feignEncoder(jaxbContextFactory: JAXBContextFactory): Encoder {
        return SOAPEncoder(jaxbContextFactory)
    }
}
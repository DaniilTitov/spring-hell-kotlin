package ru.spring.hell.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.POST
import ru.spring.hell.configuration.feign.ItemFeignClientConfiguration
import ru.spring.hell.model.soap.Items


@FeignClient("items", url = "http://0.0.0.0:8080", configuration = [ItemFeignClientConfiguration::class])
interface ItemClient {

    @RequestMapping(method = [POST], value = ["/ws"], consumes = ["text/xml"])
    fun items(request: Items?): Items?
}

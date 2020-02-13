package ru.spring.hell.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.*
import ru.spring.hell.configuration.feign.PersonFeignClientConfiguration
import ru.spring.hell.model.Person


@FeignClient("persons", url = "http://0.0.0.0:8080", configuration = [PersonFeignClientConfiguration::class])
interface PersonClient {
    @RequestMapping(method = [GET], value = ["/persons"])
    fun persons(): List<Person>

    @RequestMapping(method = [POST], value = ["/person/{personId}"], consumes = ["application/json"])
    fun update(@PathVariable("personId") personId: String?, person: Person?): Person?

    @RequestMapping(method = [GET], value = ["/person/{personId}"], consumes = ["application/json"])
    fun get(@PathVariable("personId") personId: String?): Person?

    @RequestMapping(method = [POST], value = ["/person"], consumes = ["application/json"])
    fun create(person: Person?): Person?

    @RequestMapping(method = [DELETE], value = ["/person/{personId}"], consumes = ["application/json"])
    fun delete(@PathVariable("personId") personId: String?): Person?
}
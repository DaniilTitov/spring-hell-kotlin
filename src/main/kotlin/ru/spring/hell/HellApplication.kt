package ru.spring.hell

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HellApplication

fun main(args: Array<String>) {
	runApplication<HellApplication>(*args)
}

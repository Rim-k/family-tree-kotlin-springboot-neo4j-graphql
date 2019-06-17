package com.cisse.demo

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application {

	@Bean
	fun init() = CommandLineRunner {
		print("Hello")
	}
}

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}

package com.cisse.demo

import com.cisse.demo.core.domain.usecases.person.GetPerson
import com.cisse.demo.data.neo4j.repositories.PersonRepository
import org.neo4j.driver.v1.GraphDatabase
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application {

	@Bean
	fun init(getPerson: GetPerson) = CommandLineRunner {
		val siblings = getPerson.siblings("58aa01a0-90f3-11e9-aa6c-0242ac140002")
		print(siblings)
	}
}

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}

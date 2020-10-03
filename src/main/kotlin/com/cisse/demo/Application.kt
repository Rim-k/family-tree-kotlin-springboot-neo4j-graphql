package com.cisse.demo

import ac.simons.neo4j.migrations.core.Migrations
import ac.simons.neo4j.migrations.core.MigrationsConfig
import org.neo4j.driver.AuthTokens
import org.neo4j.driver.GraphDatabase
import org.neo4j.ogm.config.Configuration
import org.neo4j.ogm.config.UsernamePasswordCredentials
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.retry.RetryCallback
import org.springframework.retry.support.RetryTemplate
import java.io.IOException

@SpringBootApplication
class Application {

	@Bean
	fun init(neo4jConfiguration: Configuration) = CommandLineRunner {

		// don't do that in real life, put the config values in the properties file :-)
		RetryTemplate.builder()
				.maxAttempts(3)
				.exponentialBackoff(20000, 2.0, 30000)
				.retryOn(IOException::class.java)
				.traversingCauses()
				.build()
				.execute(RetryCallback { migrations(neo4jConfiguration).apply() })
	}

	private fun migrations(neo4jConfiguration: Configuration): Migrations {

		val usernameAndPassword = checkNotNull(neo4jConfiguration.credentials) as UsernamePasswordCredentials

		return Migrations(
				MigrationsConfig.builder().build(),
				GraphDatabase.driver(
						neo4jConfiguration.uri,
						AuthTokens.basic(usernameAndPassword.username, usernameAndPassword.password)
				)
		)
	}

}

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}

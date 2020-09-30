import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.3.4.RELEASE"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	kotlin("jvm") version "1.4.10"
	kotlin("plugin.spring") version "1.4.10"
}

val kgraphql_version = "0.15.0"
val junit_version = "5.4.2"
val gson_version = "2.8.5"

group = "com.cisse"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8


repositories {
	mavenCentral()
	maven("https://jcenter.bintray.com/")
}

dependencies {

	// Spring boot
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-devtools")

	// Neo4j
	implementation("org.springframework.boot:spring-boot-starter-data-neo4j")

	implementation("eu.michael-simons.neo4j:neo4j-migrations-spring-boot-starter:0.0.13")
	//compile group: 'eu.michael-simons.neo4j', name: 'neo4j-migrations-spring-boot-starter', version: '0.0.13'

	// Jackson
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	// Kotlin
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	// GraphQL
	implementation("com.apurebase:kgraphql-ktor:$kgraphql_version")

	// Json
	implementation("com.google.code.gson:gson:$gson_version")

	// Testing
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("org.junit.jupiter:junit-jupiter:$junit_version")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junit_version")
}


tasks.withType<Test> {
	useJUnitPlatform()
	testLogging {
		events("passed", "skipped", "failed")
	}
}

tasks.withType<Wrapper> {
	gradleVersion = "5.4.1"
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

springBoot {
	mainClassName = "com.cisse.demo.ApplicationKt"
}

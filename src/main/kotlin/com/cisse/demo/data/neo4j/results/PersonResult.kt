package com.cisse.demo.data.neo4j.results

import com.cisse.demo.api.dto.PersonDTO
import com.cisse.demo.core.domain.entities.Person
import org.springframework.data.neo4j.annotation.QueryResult

@QueryResult
data class PersonResult(
        val person: Person,
        val mother: Person?,
        val father: Person?,
        val spouse: Person?,
        val siblings: MutableSet<Person>,
        val friends: MutableSet<Person>
        ) {

    fun toPersonDTO() : PersonDTO {
        val personDTO = person.toPersonDTO()
        personDTO.mother = mother?.toPersonDTO()
        personDTO.father = father?.toPersonDTO()
        personDTO.spouse = spouse?.toPersonDTO()
        personDTO.siblings.addAll(siblings.map { it.toPersonDTO() })
        personDTO.friends.addAll(friends.map { it.toPersonDTO() })

        return personDTO
    }
}
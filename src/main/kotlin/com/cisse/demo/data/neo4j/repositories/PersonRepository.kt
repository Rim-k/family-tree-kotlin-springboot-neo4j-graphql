package com.cisse.demo.data.neo4j.repositories

import com.cisse.demo.api.dto.PersonDTO
import com.cisse.demo.core.domain.entities.Person
import com.cisse.demo.data.neo4j.results.PersonResult
import org.springframework.data.neo4j.annotation.Query
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : Neo4jRepository<Person, String> {

    //@Query(FIND_BY_FIRSTNAME_WITH_SIBLINGS)
    fun findByFirstname(@Param("firstname") firstname: String): Collection<PersonResult>

    fun findByLastname(@Param("lastname") lastname: String): Collection<PersonResult>

    fun findByUuid(@Param("uuid") uuid: String): PersonResult?

    @Query(FIND_SIBLINGS)
    fun findSiblingsOf(@Param("uuid") uuid: String): MutableSet<Person>

    companion object {
        const val FIND_BY_FIRSTNAME_WITH_SIBLINGS = """MATCH (person:Person {firstname: {firstname}})
                        WITH person
                        OPTIONAL MATCH (person)-[:HAS_MOTHER]->(mother)
                        OPTIONAL MATCH (person)-[:HAS_FATHER]->(father)
                        OPTIONAL MATCH (person)-[:SPOUSE_OF]-(spouse)
                        OPTIONAL MATCH (person)-[:HAS_MOTHER|HAS_FATHER]->(Parent)<-[:HAS_MOTHER|HAS_FATHER]-(siblings)
                        RETURN person, spouse, mother, father, collect(distinct siblings) as siblings"""

        const val FIND_SIBLINGS = """MATCH (person:Person {uuid: {0}})
                                     RETURN person
                                  """
    }

}


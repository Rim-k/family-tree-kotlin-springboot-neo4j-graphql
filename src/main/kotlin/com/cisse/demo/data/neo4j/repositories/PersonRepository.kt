package com.cisse.demo.data.neo4j.repositories

import com.cisse.demo.core.domain.entities.Person
import org.springframework.data.neo4j.annotation.Query
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : Neo4jRepository<Person, String> {

    @Query("MATCH (person:Person {firstname: {firstname}}) RETURN person")
    fun findByFirstname(@Param("firstname") firstname: String): Collection<Person>

    @Query("MATCH (person:Person {lastname: {lastname}}) RETURN person")
    fun findByLastname(@Param("lastname") lastname: String): Collection<Person>

    @Query("MATCH (person:Person {uuid: {uuid}}) RETURN person")
    fun findByUuid(@Param("uuid") uuid: String): Person

    @Query(FIND_SIBLINGS)
    fun findSiblingsOf(@Param("uuid") uuid: String): MutableSet<Person>

    @Query(FIND_SPOUSE)
    fun findSpouseOf(@Param("uuid") uuid: String): Person?

    @Query(FIND_MOTHER)
    fun findMotherOf(@Param("uuid") uuid: String): Person?

    @Query(FIND_FATHER)
    fun findFatherOf(@Param("uuid") uuid: String): Person?

    @Query(FIND_FRIENDS)
    fun findFriendsOf(@Param("uuid") uuid: String): MutableSet<Person>

    companion object {
        const val FIND_BY_FIRSTNAME_WITH_SIBLINGS = """MATCH (person:Person {firstname: {firstname}})
                        WITH person
                        OPTIONAL MATCH (person)-[:HAS_MOTHER]->(mother)
                        OPTIONAL MATCH (person)-[:HAS_FATHER]->(father)
                        OPTIONAL MATCH (person)-[:SPOUSE_OF]-(spouse)
                        OPTIONAL MATCH (person)-[:HAS_MOTHER|HAS_FATHER]->(Parent)<-[:HAS_MOTHER|HAS_FATHER]-(siblings)
                        RETURN person, spouse, mother, father, collect(distinct siblings) as siblings"""

        const val FIND_SIBLINGS = """MATCH (person:Person {uuid: {0}})
                                     WITH person
                                     MATCH (person)-[:HAS_MOTHER|HAS_FATHER]->(Parent)<-[:HAS_MOTHER|HAS_FATHER]-(siblings)
                                     RETURN distinct siblings
                                  """

        const val FIND_SPOUSE = """MATCH (:Person {uuid: {0}})-[:SPOUSE_OF]-(spouse)
                                   RETURN spouse
                                """

        const val FIND_MOTHER = """MATCH (:Person {uuid: {0}})-[:HAS_MOTHER]->(mother)
                                   RETURN mother
                                """

        const val FIND_FATHER = """MATCH (:Person {uuid: {0}})-[:HAS_FATHER]->(father)
                                   RETURN father
                                """

        const val FIND_FRIENDS = """MATCH (:Person {uuid: {0}})-[:FRIEND_OF]-(friends)
                                    RETURN friends
                                """
    }

}


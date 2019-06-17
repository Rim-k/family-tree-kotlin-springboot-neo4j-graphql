package com.cisse.demo.core.domain.entities

import com.cisse.demo.api.dto.Gender
import com.cisse.demo.api.dto.PersonDTO
import com.cisse.demo.data.neo4j.config.DateConverter
import org.neo4j.ogm.annotation.*
import org.neo4j.ogm.annotation.Relationship
import org.neo4j.ogm.annotation.typeconversion.Convert
import java.time.LocalDate

@NodeEntity
class Person(
        @Index(unique=true, primary = true) @Property(name = "uuid") var uuid: String,
        val firstname: String,
        val lastname: String,
        val birthName: String? = null,
        @Convert(DateConverter::class) val birthdate: LocalDate,
        val gender: Gender,
        @Relationship("SPOUSE_OF") var spouse: Person? = null,
        @Relationship("HAS_MOTHER") var mother: Person? = null,
        @Relationship("HAS_FATHER") var father: Person? = null,
        @Relationship("SIBLING_OF") var siblings: MutableSet<Person> = HashSet(),
        @Relationship("FRIEND_OF") var friends: MutableSet<Person> = HashSet()

){

    /**
     * Title is our primary index and merge key, therefore equals contract is based on it.
     */
    override fun equals(other: Any?): Boolean = when(other) {
        null -> false
        this -> true
        is Person -> uuid == other.uuid
        else -> false
    }

    /**
     * Title is our primary index and merge key, therefore hashCode contract is based on it.
     */
    override fun hashCode(): Int {
        return uuid.hashCode()
    }

    fun toPersonDTO() = PersonDTO(
        uuid = uuid,
        firstname = firstname,
        lastname = lastname,
        birthName = birthName,
        birthdate = birthdate,
        gender = gender,
        spouse = spouse?.let {
            PersonDTO(it.uuid, it.firstname, it.lastname, it.birthName, it.birthdate, it.gender)
        },
        mother = mother?.let {
            PersonDTO(it.uuid, it.firstname, it.lastname, it.birthName, it.birthdate, it.gender)
        },
        father = father?.let {
            PersonDTO(it.uuid, it.firstname, it.lastname, it.birthName, it.birthdate, it.gender)
        },
        siblings = siblings.map { PersonDTO(it.uuid, it.firstname, it.lastname, it.birthName, it.birthdate, it.gender) }.toMutableSet(),
        friends = friends.map { PersonDTO(it.uuid, it.firstname, it.lastname, it.birthName, it.birthdate, it.gender) }.toMutableSet()
    )
}




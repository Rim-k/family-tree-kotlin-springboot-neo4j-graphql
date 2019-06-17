package com.cisse.demo.api.dto

import java.time.LocalDate

data class PersonDTO(
        val uuid: String,
        val firstname: String,
        val lastname: String,
        val birthName: String?=null,
        val birthdate: LocalDate,
        val gender: Gender,
        var mother: PersonDTO? = null,
        var father: PersonDTO? = null,
        var spouse: PersonDTO? = null,
        val siblings: MutableSet<PersonDTO> = HashSet(),
        val friends: MutableSet<PersonDTO> = HashSet()
)

enum class Gender {
    MALE,
    FEMALE
}
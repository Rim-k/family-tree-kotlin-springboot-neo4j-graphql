package com.cisse.demo.core.domain.usecases.person

import com.cisse.demo.api.dto.PersonDTO

interface GetPerson {
    fun byUuid(uuid: String): PersonDTO?
    fun byFirstname(firstname: String): Collection<PersonDTO>
    fun byLastname(lastname: String): Collection<PersonDTO>
    fun all(size: Int?): Collection<PersonDTO>
    fun siblings(uuid: String): Collection<PersonDTO>
}
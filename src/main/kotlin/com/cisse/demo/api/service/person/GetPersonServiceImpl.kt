package com.cisse.demo.api.service.person

import com.cisse.demo.api.dto.PersonDTO
import com.cisse.demo.core.domain.usecases.person.GetPerson
import com.cisse.demo.data.neo4j.repositories.PersonRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class GetPersonImpl(val personRepository: PersonRepository) : GetPerson {

    override fun siblings(uuid: String) = personRepository.findSiblingsOf(uuid).map { it.toPersonDTO() }

    override fun byUuid(uuid: String) = personRepository.findByUuid(uuid)?.toPersonDTO()

    override fun byFirstname(firstname: String) = personRepository.findByFirstname(firstname).map { it.toPersonDTO() }

    override fun byLastname(lastname: String) = personRepository.findByLastname(lastname).map { it.toPersonDTO() }

    override fun all(size: Int?) = personRepository.findAll().map { it.toPersonDTO() }


}
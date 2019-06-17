package com.cisse.demo.api.dto

import org.springframework.data.neo4j.annotation.QueryResult

@QueryResult
data class RelationshipDTO(
        private val uuid: String?,
        private val from: PersonDTO,
        private val to: PersonDTO,
        private val relationshipType: RelationshipType
)

enum class RelationshipType {
    HAS_MOTHER,
    HAS_FATHER,
    SPOUSE_OF,
    SIBLING_OF,
    FRIEND_OF
}
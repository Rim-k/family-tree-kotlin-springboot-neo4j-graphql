package com.cisse.demo.core.domain.entities
import com.cisse.demo.api.dto.RelationshipDTO
import com.cisse.demo.api.dto.RelationshipType
import org.neo4j.ogm.annotation.EndNode
import org.neo4j.ogm.annotation.RelationshipEntity
import org.neo4j.ogm.annotation.StartNode

abstract class Relationship(
        uuid: String,
        @StartNode val from: Person,
        @EndNode val to: Person,
        val relationshipType: RelationshipType
) : Entity(uuid) {

    override fun equals(other: Any?): Boolean = when(other) {
        null -> false
        this -> true
        is Relationship -> uuid == other.uuid
        else -> false
    }

    override fun hashCode(): Int {
        return uuid.hashCode()
    }

    fun toRelationship() = RelationshipDTO(
            uuid = uuid,
            from = from.toPersonDTO(),
            to = to.toPersonDTO(),
            relationshipType = relationshipType
    )
}

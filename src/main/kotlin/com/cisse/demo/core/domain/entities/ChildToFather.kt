package com.cisse.demo.core.domain.entities

import com.cisse.demo.api.dto.RelationshipType
import org.neo4j.ogm.annotation.RelationshipEntity

@RelationshipEntity("HAS_FATHER")
class ChildToFather(uuid: String,
                    from: Person,
                    to: Person,
                    relationshipType: RelationshipType) : Relationship(uuid, from, to, relationshipType)
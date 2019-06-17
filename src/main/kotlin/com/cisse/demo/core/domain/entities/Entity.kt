package com.cisse.demo.core.domain.entities

import org.neo4j.ogm.annotation.GeneratedValue
import org.neo4j.ogm.annotation.Id

abstract class Entity (@Id @GeneratedValue val uuid: String)
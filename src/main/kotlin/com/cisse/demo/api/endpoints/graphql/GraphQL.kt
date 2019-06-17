package com.cisse.demo.api.endpoints.graphql

import com.github.pgutkowski.kgraphql.schema.Schema
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/graphql")
class GraphQL (appSchema: AppSchema) {

    private val schema: Schema = appSchema.schema

    val log: Logger =  LoggerFactory.getLogger(this.javaClass)

    @PostMapping
    fun graphql(@RequestBody query: String): String {
        log.info("the graphql FIND_BY_FIRSTNAME_WITH_SIBLINGS: $query")
        return schema.execute(query)
    }
}


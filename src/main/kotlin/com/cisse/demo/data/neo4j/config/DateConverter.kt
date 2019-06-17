package com.cisse.demo.data.neo4j.config

import org.neo4j.ogm.typeconversion.AttributeConverter
import java.time.LocalDate


class DateConverter : AttributeConverter<LocalDate, LocalDate> {
    override fun toGraphProperty(value: LocalDate) = value
    override fun toEntityAttribute(value: LocalDate) = value
}

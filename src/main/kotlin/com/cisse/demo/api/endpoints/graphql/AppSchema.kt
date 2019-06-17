package com.cisse.demo.api.endpoints.graphql

import com.cisse.demo.api.dto.Gender
import com.cisse.demo.api.dto.PersonDTO
import com.cisse.demo.core.domain.usecases.person.GetPerson
import com.cisse.demo.exception.NotFoundException
import com.github.pgutkowski.kgraphql.KGraphQL
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class AppSchema(private val getPerson: GetPerson) {


    val schema = KGraphQL.schema {

        configure {
            useDefaultPrettyPrinter = true
        }

        stringScalar<LocalDate> {
            serialize = { date -> date.toString() }
            deserialize = { dateString -> LocalDate.parse(dateString) }
        }

        query("people") {
            description = "Returns a subset of the people"

            resolver { size: Int? -> getPerson.all(size) }.withArgs {
                arg<Int> { name = "size"; defaultValue = 10; description = "The number of people to return" }
            }
        }

        query("person") {
            description = "Returns a single person based on the firstname"

            resolver {
                firstname: String -> getPerson.byFirstname(firstname).first()
            }
        }

        type<PersonDTO>{

            property<Collection<PersonDTO>>("siblings") {
                resolver {
                    person -> getPerson.siblings(person.uuid!!)
                }
            }
        }

        enum<Gender>{}
    }


}
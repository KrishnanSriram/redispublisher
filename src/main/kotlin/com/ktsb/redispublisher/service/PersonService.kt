package com.ktsb.redispublisher.service

import com.ktsb.redispublisher.controller.PersonController
import com.ktsb.redispublisher.model.Person
import com.ktsb.redispublisher.model.PersonDTO
import com.ktsb.redispublisher.producer.PersonProducer
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonService(val personProducer: PersonProducer) {
    companion object {
        val logger = LoggerFactory.getLogger(PersonService::class.java)
    }

    fun publishNewPerson(personDTO: PersonDTO): Person {
        val uuid: UUID = UUID.randomUUID()
        val id = uuid.toString()
        logger.info("ID for person is ${id}")
        val person = Person(id, personDTO.firstName, personDTO.lastName, personDTO.email)
        personProducer.publish(person)
        return person
    }
}
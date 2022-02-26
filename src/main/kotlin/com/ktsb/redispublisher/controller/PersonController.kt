package com.ktsb.redispublisher.controller

import com.ktsb.redispublisher.model.Person
import com.ktsb.redispublisher.model.PersonDTO
import com.ktsb.redispublisher.service.PersonService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/person")
class PersonController(val personService: PersonService) {
    companion object {
        val logger = LoggerFactory.getLogger(PersonController::class.java)
    }

    @PostMapping
    fun newPerson(@RequestBody personDTO: PersonDTO): ResponseEntity<Person> {
        logger.info("Received person $personDTO")
        val person = personService.publishNewPerson(personDTO)
        return ResponseEntity(person, HttpStatus.OK)
    }
}
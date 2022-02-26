package com.ktsb.redispublisher.model

data class PersonDTO(val firstName: String, val lastName: String, val email: String)
data class Person(val personId: String, val firstName: String, val lastName: String, val email: String)
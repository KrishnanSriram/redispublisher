package com.ktsb.redispublisher.controller

import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class HomeController(val env: Environment) {
    @GetMapping
    fun home(): ResponseEntity<String> {
        return ResponseEntity(env.getProperty("app.message"), HttpStatus.OK)
    }
}
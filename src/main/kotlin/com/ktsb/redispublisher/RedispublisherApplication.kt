package com.ktsb.redispublisher

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RedispublisherApplication

fun main(args: Array<String>) {
    runApplication<RedispublisherApplication>(*args)
}

package com.ktsb.redispublisher.producer

import com.ktsb.redispublisher.model.Person
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component

@Component
class PersonProducer(val template: RedisTemplate<String, Any>) {
    @Value("\${spring.redis.topic}")
    lateinit var redisTopic: String
    companion object {
        val logger = LoggerFactory.getLogger(PersonProducer::class.java)
    }

    fun publish(person: Person) {
        logger.info("Publish person")
        template.convertAndSend(redisTopic, person)
        logger.info("Published person")
    }
}
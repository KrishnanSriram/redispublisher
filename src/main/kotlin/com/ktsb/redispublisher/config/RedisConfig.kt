package com.ktsb.redispublisher.config

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer

@Configuration
class RedisConfig {

    @Value("\${spring.redis.host}")
    lateinit var redisHost: String
    @Value("\${spring.redis.port}")
    lateinit var redisPort: String
    @Value("\${spring.redis.topic}")
    lateinit var redisTopic: String

    companion object {
        val logger = LoggerFactory.getLogger(RedisConfig::class.java)
    }

    @Bean
    fun lettuceConnectionFactory(): LettuceConnectionFactory {
        logger.info("LettuceConnection Factory initialized")
        val config = RedisStandaloneConfiguration(redisHost, redisPort.toInt())
        return LettuceConnectionFactory(config)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> {
        logger.info("Create Redis template")
        val template: RedisTemplate<String, Any> = RedisTemplate()
        template.setConnectionFactory(lettuceConnectionFactory())
        template.valueSerializer = GenericJackson2JsonRedisSerializer()
        return template
    }

    @Bean
    fun newMessageListener(): MessageListenerAdapter {
        return MessageListenerAdapter()
    }

    @Bean
    fun topic(): ChannelTopic {
        return ChannelTopic(redisTopic)
    }

    @Bean
    fun redisContainer() : RedisMessageListenerContainer {
        logger.info("Create ListenerContainer")
        val container = RedisMessageListenerContainer()
        container.setConnectionFactory(lettuceConnectionFactory())
        container.addMessageListener(newMessageListener(), topic())
        return container
    }
}
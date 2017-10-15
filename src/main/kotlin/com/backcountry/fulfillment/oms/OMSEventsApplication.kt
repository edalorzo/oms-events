package com.backcountry.fulfillment.oms

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.transaction.ChainedTransactionManager
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import javax.persistence.EntityManagerFactory

@SpringBootApplication
class OMSEventsApplication

@Configuration
class OMSEventsApplicationConfiguration {

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = Jackson2JsonMessageConverter(ObjectMapper())
        return rabbitTemplate
    }

    @Bean
    fun rabbitTransactionManager(connectionFactory: ConnectionFactory): PlatformTransactionManager {
        return RabbitTransactionManager(connectionFactory)
    }

    @Bean
    fun jpaTransactionManager(emf: EntityManagerFactory): PlatformTransactionManager {
        return JpaTransactionManager(emf)
    }

    @Bean
    @Primary
    fun transactionManager(jpaTransactionManager: PlatformTransactionManager,
                           rabbitTransactionManager: PlatformTransactionManager): PlatformTransactionManager {
        return ChainedTransactionManager(rabbitTransactionManager, jpaTransactionManager)
    }

    @Bean
    fun rabbitEventBus(configurer: SimpleRabbitListenerContainerFactoryConfigurer,
                         connectionFactory: ConnectionFactory): SimpleRabbitListenerContainerFactory {

        val factory = SimpleRabbitListenerContainerFactory()
        configurer.configure(factory, connectionFactory)

        factory.setConnectionFactory(connectionFactory)
        factory.setAutoStartup(true)
        factory.setConcurrentConsumers(1)
        factory.setMaxConcurrentConsumers(1)
        factory.setMessageConverter(Jackson2JsonMessageConverter(ObjectMapper()))

        return factory
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(OMSEventsApplication::class.java, *args)
}

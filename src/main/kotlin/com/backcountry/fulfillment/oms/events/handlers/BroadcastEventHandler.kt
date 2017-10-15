package com.backcountry.fulfillment.oms.events.handlers

import com.backcountry.fulfillment.oms.events.api.Event
import com.backcountry.fulfillment.oms.events.api.EventHandler
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.event.EventListener
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Component
class BroadcastEventHandler @Autowired constructor(
        private val rabbitTemplate: RabbitTemplate) : EventHandler<Event> {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Order(1)
    @EventListener
    override fun handle(event: Event) {
        rabbitTemplate.convertAndSend("ffd.event.bus", event.getRoutingKey(), event)
        logger.info("Just broadcast event {}", event)
    }
}
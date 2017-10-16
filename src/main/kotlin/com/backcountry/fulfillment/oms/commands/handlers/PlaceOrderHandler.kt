package com.backcountry.fulfillment.oms.commands.handlers

import com.backcountry.fulfillment.cqrs.commands.api.CommandHandler
import com.backcountry.fulfillment.cqrs.commands.oms.PlaceOrder
import com.backcountry.fulfillment.cqrs.events.oms.OrderPlaced
import com.backcountry.fulfillment.oms.commands.CommandListener
import com.backcountry.fulfillment.oms.events.EventBus
import com.backcountry.fulfillment.oms.model.Order
import com.backcountry.fulfillment.oms.repositories.OrderRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PlaceOrderHandler @Autowired constructor(
        private val orderRepository: OrderRepository,
        private val eventBus: EventBus): CommandHandler<PlaceOrder> {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @CommandListener
    override fun handle(command: PlaceOrder) {
        orderRepository.save(Order(command))
        eventBus.publishEvent(OrderPlaced(command))
    }
}
package com.backcountry.fulfillment.oms.events.listeners

import com.backcountry.fulfillment.oms.commands.CreateCustomer
import com.backcountry.fulfillment.oms.commands.api.CommandBus
import com.backcountry.fulfillment.oms.events.CustomerCreated
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Transactional
@Component
@RabbitListener(queues = arrayOf("ffd.orders.customers.created"), containerFactory = "rabbitEventBus")
class CustomerListener(@Autowired private val commandBus: CommandBus) {

    @RabbitHandler
    fun createCustomer(event: CustomerCreated) {
        commandBus.publishCommand(CreateCustomer(event))
    }
}
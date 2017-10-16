package com.backcountry.fulfillment.oms.events.listeners


import com.backcountry.fulfillment.cqrs.commands.oms.ReplicateCustomer
import com.backcountry.fulfillment.cqrs.events.cms.CustomerCreated
import com.backcountry.fulfillment.oms.commands.CommandBus
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Transactional
@Component
@RabbitListener(queues = arrayOf("ffd.orders.customers.created"), containerFactory = "rabbitEventBus")
class ReplicateCustomerListener(@Autowired private val commandBus: CommandBus) {

    @RabbitHandler
    fun createCustomer(event: CustomerCreated) {
        commandBus.publishCommand(ReplicateCustomer(event))
    }
}
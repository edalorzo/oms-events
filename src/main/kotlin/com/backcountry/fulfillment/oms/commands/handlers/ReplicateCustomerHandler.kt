package com.backcountry.fulfillment.oms.commands.handlers

import com.backcountry.fulfillment.cqrs.commands.api.CommandHandler
import com.backcountry.fulfillment.cqrs.commands.oms.ReplicateCustomer
import com.backcountry.fulfillment.cqrs.events.oms.CustomerReplicated
import com.backcountry.fulfillment.oms.commands.CommandListener
import com.backcountry.fulfillment.oms.events.EventBus
import com.backcountry.fulfillment.oms.model.Customer
import com.backcountry.fulfillment.oms.repositories.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ReplicateCustomerHandler @Autowired constructor(
        private val customerRepository: CustomerRepository,
        private val eventBus: EventBus): CommandHandler<ReplicateCustomer> {

    @CommandListener
    override fun handle(command: ReplicateCustomer) {
        if(!customerRepository.findById(command.email).isPresent) {
            customerRepository.save(Customer(command))
        }

        eventBus.publishEvent(CustomerReplicated(command))

    }

}
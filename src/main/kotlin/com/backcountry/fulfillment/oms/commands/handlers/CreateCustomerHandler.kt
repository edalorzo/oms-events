package com.backcountry.fulfillment.oms.commands.handlers

import com.backcountry.fulfillment.oms.commands.CreateCustomer
import com.backcountry.fulfillment.oms.commands.api.CommandHandler
import com.backcountry.fulfillment.oms.commands.api.CommandListener
import com.backcountry.fulfillment.oms.model.Customer
import com.backcountry.fulfillment.oms.repositories.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CreateCustomerHandler(@Autowired val customerRepository: CustomerRepository): CommandHandler<CreateCustomer> {

    @CommandListener
    override fun handle(command: CreateCustomer) {
        if(!customerRepository.findById(command.email).isPresent) {
            customerRepository.save(Customer(command))
        }
    }

}
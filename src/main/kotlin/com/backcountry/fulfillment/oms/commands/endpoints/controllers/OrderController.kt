package com.backcountry.fulfillment.oms.commands.endpoints.controllers

import com.backcountry.fulfillment.cqrs.commands.oms.PlaceOrder
import com.backcountry.fulfillment.oms.commands.CommandBus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Transactional
@RestController
@RequestMapping("/orders")
class OrderController@Autowired constructor(private val commandBus: CommandBus) {

    @PutMapping("/place-order")
    fun placeOrder(@RequestBody command: PlaceOrder) {
        commandBus.publishCommand(command);
    }

}
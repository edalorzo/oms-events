package com.backcountry.fulfillment.oms.commands.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component
import java.util.*

@Component
class CommandBus(@Autowired private val publisher: ApplicationEventPublisher) {

    fun publishCommand(command: Command) {
        Objects.requireNonNull(command, "The command must not be null")
        publisher.publishEvent(command)
    }

}
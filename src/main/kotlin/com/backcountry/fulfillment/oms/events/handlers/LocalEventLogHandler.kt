package com.backcountry.fulfillment.oms.events.handlers

import com.backcountry.fulfillment.oms.events.api.Event
import com.backcountry.fulfillment.oms.events.api.EventHandler
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase.AFTER_COMPLETION
import org.springframework.transaction.event.TransactionalEventListener

@Component
class LocalEventLogHandler : EventHandler<Event> {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @TransactionalEventListener(phase = AFTER_COMPLETION)
    override fun handle(event: Event) {
        logger.info("The event {} just happened", event)
    }
}
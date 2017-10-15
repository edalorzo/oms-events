package com.backcountry.fulfillment.oms.events.api

interface EventHandler<T> where T: Event {

    fun handle(event: T)

}
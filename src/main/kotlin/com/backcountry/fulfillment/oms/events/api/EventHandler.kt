package com.backcountry.fulfillment.oms.events.api

interface EventHandler<in T> where T: Event {

    fun handle(event: T)

}
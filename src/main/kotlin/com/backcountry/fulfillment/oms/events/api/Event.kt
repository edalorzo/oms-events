package com.backcountry.fulfillment.oms.events.api

import com.fasterxml.jackson.annotation.JsonIgnore

interface Event {

    @JsonIgnore
    fun getRoutingKey(): String

}
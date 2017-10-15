package com.backcountry.fulfillment.oms.events

import com.backcountry.fulfillment.oms.events.api.Event
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty


data class CustomerCreated @JsonCreator constructor(
        @JsonProperty("email") val email: String,
        @JsonProperty("firstName") val firstName: String,
        @JsonProperty("lastName") val lastName: String) : Event {

    override fun getRoutingKey() = "ffd.Customer.CustomerCreated"
}
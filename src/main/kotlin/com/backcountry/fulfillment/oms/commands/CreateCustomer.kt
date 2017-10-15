package com.backcountry.fulfillment.oms.commands

import com.backcountry.fulfillment.oms.commands.api.Command
import com.backcountry.fulfillment.oms.events.CustomerCreated
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class CreateCustomer @JsonCreator constructor(
        @JsonProperty("email") val email: String,
        @JsonProperty("firstName") val firstName: String,
        @JsonProperty("lastName") val lastName: String
): Command {

 constructor(event: CustomerCreated): this(event.email, event.firstName, event.lastName)
}
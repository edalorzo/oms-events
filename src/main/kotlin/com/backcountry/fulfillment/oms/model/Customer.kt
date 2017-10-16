package com.backcountry.fulfillment.oms.model

import com.backcountry.fulfillment.cqrs.commands.oms.ReplicateCustomer
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "customers")
class Customer(
        @Id @Column(name="cust_email") val email: String,
        @Column(name="cust_fname") val firstName: String,
        @Column(name="cust_lname") val lastName: String,
        @Column(name = "cust_replicated") val isReplicated: Boolean = false) {

    constructor(command: ReplicateCustomer):this(
            command.email,
            command.firstName,
            command.lastName
    )

}
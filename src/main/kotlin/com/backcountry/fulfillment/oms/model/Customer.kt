package com.backcountry.fulfillment.oms.model

import com.backcountry.fulfillment.oms.commands.CreateCustomer
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "customers")
class Customer(
        @Id @Column(name="cust_email") var email: String,
        @Column(name="cust_fname") var firstName: String,
        @Column(name="cust_lname") var lastName: String) {

    constructor(command: CreateCustomer):this(
            command.email,
            command.firstName,
            command.lastName
    )

}
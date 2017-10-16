package com.backcountry.fulfillment.oms.model

import com.backcountry.fulfillment.cqrs.commands.oms.PlaceOrder
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name = "orders")
class Order(

        @Id @Column(name = "order_number")
        val orderId: String,

        @Column(name = "order_status")
        val status: String,

        @Column(name = "order_date")
        val orderDate: ZonedDateTime,

        @ManyToOne(cascade = arrayOf(CascadeType.PERSIST, CascadeType.MERGE))
        @JoinColumn(name = "cust_email")
        val customer: Customer,

        @ElementCollection
        @CollectionTable(name = "order_lines", joinColumns = arrayOf(JoinColumn(name = "order_number")))
        val lines: Collection<OrderLine>) {

    constructor(command: PlaceOrder): this(
            command.order.orderId,
            "CREATED",
            ZonedDateTime.now(),
            Customer(
                    command.order.customer.email,
                    command.order.customer.firstName,
                    command.order.customer.lastName),
            command.order.lines.map { l -> OrderLine(l.sku, l.price, l.quantity) }
    )
}
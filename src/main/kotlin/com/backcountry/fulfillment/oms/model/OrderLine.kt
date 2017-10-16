package com.backcountry.fulfillment.oms.model

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class OrderLine(
        @Column(name = "prod_sku") val sku: String,
        @Column(name = "prod_price") val price: Double,
        @Column(name = "quantity") val quantity: Int
)
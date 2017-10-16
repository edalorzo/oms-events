package com.backcountry.fulfillment.oms.repositories

import com.backcountry.fulfillment.oms.model.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<Order, String>
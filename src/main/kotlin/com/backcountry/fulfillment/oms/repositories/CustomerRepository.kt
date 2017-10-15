package com.backcountry.fulfillment.oms.repositories

import com.backcountry.fulfillment.oms.model.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository: JpaRepository<Customer, String>
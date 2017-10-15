package com.backcountry.fulfillment.oms

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class OMSEventsApplication

fun main(args: Array<String>) {
    SpringApplication.run(OMSEventsApplication::class.java, *args)
}

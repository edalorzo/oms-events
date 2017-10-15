package com.backcountry.fulfillment.oms.commands.api

interface CommandHandler<in T> where T: Command {
    
    fun handle(command: T)
}
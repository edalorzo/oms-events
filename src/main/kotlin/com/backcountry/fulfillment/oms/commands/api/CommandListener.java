package com.backcountry.fulfillment.oms.commands.api;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EventListener
public @interface CommandListener {

    @AliasFor(annotation = EventListener.class)
    Class<?>[] value() default {};

    @AliasFor(annotation = EventListener.class)
    Class<?>[] classes() default {};

    @AliasFor(annotation = EventListener.class)
    String condition() default "";

}

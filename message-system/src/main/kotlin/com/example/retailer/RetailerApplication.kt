package com.example.retailer

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class RetailerApplication {
	@Bean
	fun topic(): TopicExchange {
		return TopicExchange("distributor_exchange", true, false)
	}

	@Bean
	fun retailerQueue(): Queue {
		return Queue("retailer", true, true, false)
	}

	@Bean
	fun bindingRetailer(
		topic: TopicExchange,
		retailerQueue: Queue
	): Binding {
		return BindingBuilder
			.bind(retailerQueue)
			.to(topic)
			.with("retailer.fatron558.#")
	}
}

fun main(args: Array<String>) {
	runApplication<RetailerApplication>(*args)
}




package com.example.retailer.adapter

import com.example.retailer.api.distributor.Order
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class DistributorPublisherImpl(
    @Autowired
    private val template: RabbitTemplate,
    @Autowired
    private val topic: TopicExchange
) : DistributorPublisher {

    private val objectMapper = jacksonObjectMapper()

    override fun placeOrder(order: Order): Boolean {
        return if (order.id != null) {
            val orderJson = objectMapper.writeValueAsString(order)
            template.convertAndSend(topic.name, "distributor.placeOrder.fatron558.${order.id}", orderJson) {
                it.messageProperties.headers["Notify-Exchange"] = "distributor_exchange"
                it.messageProperties.headers["Notify-RoutingKey"] = "retailer.fatron558"
                it
            }
            true
        } else {
            false
        }
    }
}
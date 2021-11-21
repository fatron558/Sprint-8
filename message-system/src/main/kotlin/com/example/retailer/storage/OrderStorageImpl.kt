package com.example.retailer.storage

import com.example.retailer.api.distributor.Order
import com.example.retailer.api.distributor.OrderInfo
import com.example.retailer.api.distributor.OrderStatus
import com.example.retailer.storage.repository.OrderInfoRepository
import com.example.retailer.storage.repository.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class OrderStorageImpl(
    @Autowired
    var orderRepository: OrderRepository,
    @Autowired
    var orderInfoRepository: OrderInfoRepository
) : OrderStorage {

    override fun createOrder(draftOrder: Order): PlaceOrderData {
        val order = orderRepository.save(draftOrder)

        val orderInfo = orderInfoRepository
            .save(OrderInfo(order.id!!, OrderStatus.SENT, order.hashCode().toString()))
        return PlaceOrderData(order, orderInfo)
    }

    override fun updateOrder(order: OrderInfo): Boolean {
        if (getOrderInfo(order.orderId) != null) {
            orderInfoRepository.save(order)
            return true
        }
        return false
    }

    override fun getOrderInfo(id: String): OrderInfo? {
        val orderInfo = orderInfoRepository.findById(id)
        return if (orderInfo.isPresent) {
            orderInfo.get()
        } else {
            null
        }
    }
}
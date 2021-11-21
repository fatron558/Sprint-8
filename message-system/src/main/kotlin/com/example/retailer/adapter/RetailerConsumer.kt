package com.example.retailer.adapter


interface RetailerConsumer {
    /**
    * Метод для получения сообщения
    */
    fun receive(incomingMsg: String)
}
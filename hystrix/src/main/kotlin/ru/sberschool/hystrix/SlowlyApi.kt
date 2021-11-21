package ru.sberschool.hystrix

import feign.RequestLine

interface SlowlyApi {
    @RequestLine("GET /item-category/stat-boosts")
    fun getItemCategory(): ItemCategory
}



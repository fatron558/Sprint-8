package ru.sberschool.hystrix

class FallbackSlowlyApi : SlowlyApi {
    override fun getItemCategory() = ItemCategory("fallback")
}



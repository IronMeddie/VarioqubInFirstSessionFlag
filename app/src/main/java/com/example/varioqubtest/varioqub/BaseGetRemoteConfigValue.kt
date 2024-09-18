package com.example.varioqubtest.varioqub

abstract class BaseGetRemoteConfigValue<T : Any>(
    protected val key: String,
    private val withCache: Boolean,
    private val getValueMethod: (key: String, default: T) -> T,
    private val default: T,
) {

    private lateinit var cache: T

    operator fun invoke(): T {
        if (withCache && this::cache.isInitialized) {
            return cache
        }
        cache = getValueMethod(key, default)
        return cache
    }
}



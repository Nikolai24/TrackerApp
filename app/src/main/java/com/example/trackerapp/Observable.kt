package com.example.trackerapp

typealias Observer<T> = (T) -> Unit

class Observable<T>(value: T) {
    private val callbacks = mutableListOf<(T) -> Unit>()
    var value: T = value
        set(value) {
            field = value
            callbacks.forEach { it(value) }
        }
    fun subscribe(observer: Observer<T>) {
        callbacks.add(observer)
    }
    fun unsubscribe(observer: Observer<T>) {
        callbacks.remove(observer)
    }
}

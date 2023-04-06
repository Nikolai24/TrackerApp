package com.example.trackerapp

import java.io.Serializable

data class Habit(
    var name: String? = null,
    var description: String? = null,
    var priority: String? = null,
    var type: String? = null,
    var quantity: Int? = null,
    var periodicity: Int? = null,
    var color: Int? = null,
    var id: Int? = null
): Serializable
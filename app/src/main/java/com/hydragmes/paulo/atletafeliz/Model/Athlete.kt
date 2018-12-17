package com.hydragmes.paulo.atletafeliz.Model

import java.util.*

class Athlete {
    companion object Athlete {
        fun create() = Athlete()
    }
    var uid: String? = null
    var name: String? = null
    var email: String? = null
    var state: String? = null
    var city: String? = null
    var sport: String? = null
    var birthday: Date? = null
    var bio: String? = null
}
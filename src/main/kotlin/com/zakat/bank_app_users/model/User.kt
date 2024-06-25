package com.zakat.bank_app_users.model

data class User(
    var id: String,

    var email: String,
    var emailVerified: Boolean,

    var firstName: String,
    var lastName: String,

    var passport: Passport,
)
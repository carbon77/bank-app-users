package com.zakat.bank_app_users.model

import java.util.Date

data class Passport(
    var number: String,
    var series: String,
    var birthday: Date,
    var issueDate: Date,
    var departmentCode: String,
)

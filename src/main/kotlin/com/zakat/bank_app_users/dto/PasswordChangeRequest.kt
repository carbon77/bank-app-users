package com.zakat.bank_app_users.dto

data class PasswordChangeRequest(
    val oldPassword: String,
    val newPassword: String,
)

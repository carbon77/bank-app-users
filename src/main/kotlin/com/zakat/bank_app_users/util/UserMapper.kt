package com.zakat.bank_app_users.util

import com.zakat.bank_app_users.model.Passport
import com.zakat.bank_app_users.model.User
import org.keycloak.representations.idm.UserRepresentation
import java.text.SimpleDateFormat

class UserMapper {

    companion object {
        fun fromKeycloakToUser(userRepresentation: UserRepresentation): User {
            val formatter = SimpleDateFormat("yyyy-MM-dd")
            val passport = Passport(
                series = userRepresentation.attributes["passport_series"]!![0],
                number = userRepresentation.attributes["passport_number"]!![0],
                departmentCode = userRepresentation.attributes["department_code"]!![0],
                birthday = formatter.parse(userRepresentation.attributes["birthday"]!![0]),
                issueDate = formatter.parse(userRepresentation.attributes["issue_date"]!![0]),
            )
            val user = User(
                id = userRepresentation.id,
                email = userRepresentation.email,
                emailVerified = userRepresentation.isEmailVerified,
                firstName = userRepresentation.firstName,
                lastName = userRepresentation.lastName,
                passport = passport,
            )
            return user
        }
    }
}
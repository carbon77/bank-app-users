package com.zakat.bank_app_users.service

import com.zakat.bank_app_users.dto.PasswordChangeRequest
import com.zakat.bank_app_users.model.User
import com.zakat.bank_app_users.util.UserMapper
import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.resource.UserResource
import org.keycloak.representations.idm.CredentialRepresentation
import org.keycloak.representations.idm.UserRepresentation
import org.springframework.stereotype.Service
import java.security.Principal
import java.util.logging.Logger

private val logger: Logger = Logger.getLogger("UserService")

@Service
class UserService(
    private val keycloak: Keycloak,
) {
    private val REALM_NAME: String = "bank-app"

    fun searchByEmail(email: String, exact: Boolean = false): List<User> {
        logger.info("Searching by email: $email (exact $exact)")
        val users = keycloak.realm(REALM_NAME)
            .users()
            .searchByEmail(email, exact)

        return users.map { UserMapper.fromKeycloakToUser(it) }
    }

    fun changePassword(principal: Principal, req: PasswordChangeRequest): MutableList<CredentialRepresentation> {
        logger.info("Change password")
        val rep = CredentialRepresentation()
        rep.type = "password"
        rep.isTemporary = false
        rep.value = req.newPassword
        val user = getUserResource(principal)
        user.resetPassword(rep)
        return user.credentials()
    }

    fun sendVerifyEmail(principal: Principal) {
        logger.info("Send verify email")
        val user = getUserResource(principal)
        user.sendVerifyEmail()
    }

    fun findUserRepresentations(): List<UserRepresentation> {
        logger.info("Find user representations")
        val users = keycloak.realm(REALM_NAME)
            .users()
            .list()

        return users
    }

    private fun getUserResource(principal: Principal): UserResource = keycloak
        .realm(REALM_NAME)
        .users()
        .get(principal.name)
}
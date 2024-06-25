package com.zakat.bank_app_users.controller

import com.zakat.bank_app_users.dto.PasswordChangeRequest
import com.zakat.bank_app_users.model.User
import com.zakat.bank_app_users.service.UserService
import jakarta.ws.rs.QueryParam
import org.keycloak.representations.idm.CredentialRepresentation
import org.keycloak.representations.idm.UserRepresentation
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
class UserController(
    private val userService: UserService,
) {

    @GetMapping
    fun find(
        @QueryParam("email") email: String,
    ): List<User> {
        return userService.searchByEmail(email)
    }

    @GetMapping("/reps")
    fun findUserRepresentations(): List<UserRepresentation> {
        return userService.findUserRepresentations()
    }

    @GetMapping("/sendVerifyEmail")
    fun sendVerifyEmail(principal: Principal) {
        return userService.sendVerifyEmail(principal)
    }

    @PostMapping("/changePassword")
    fun changePassword(
        principal: Principal,
        @RequestBody req: PasswordChangeRequest
    ): MutableList<CredentialRepresentation> {
        return userService.changePassword(principal, req)
    }
}
package com.zakat.bank_app_users

import org.keycloak.OAuth2Constants
import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.KeycloakBuilder
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class BankAppUsersApplication {

	@Bean
	fun keycloack(): Keycloak = KeycloakBuilder.builder()
		.serverUrl("http://localhost:8083")
		.realm("master")
		.clientId("admin-cli")
		.grantType(OAuth2Constants.PASSWORD)
		.username("admin")
		.password("admin")
		.build()
}

fun main(args: Array<String>) {
	runApplication<BankAppUsersApplication>(*args)
}

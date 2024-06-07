package com.teamsparta.todolist.infra.security.jwt

import com.teamsparta.todolist.infra.security.UserPrincipal
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.web.authentication.WebAuthenticationDetails
import java.io.Serializable

class JwtAuthenticationToken(
    private val principal: UserPrincipal,
    private val details: WebAuthenticationDetails
) : AbstractAuthenticationToken(principal.authorities), Serializable {

    init {
        super.setAuthenticated(true)
        super.setDetails(details)
    }

    override fun getPrincipal() = principal
    override fun getCredentials() = null
    override fun isAuthenticated() = true

}
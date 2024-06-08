package com.teamsparta.todolist.infra.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwt
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.time.Instant
import java.util.*

@Component
class JwtPlugin(
    @Value("\${auth.jwt.key}")
    private val secret: String,

    @Value("\${auth.jwt.issuer}")
    private val issuer: String,

    @Value("\${auth.jwt.expireduration}")
    private val expiration: Long
) {
    fun validateToken(jwt: String) : Result<Jws<Claims>> {
        return kotlin.runCatching {
            val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
            Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt)
        }
    }

    fun generateAccessToken(subject: String, account: String, role: String) : String {
        return generateToken(subject, account, role, Duration.ofHours(expiration))
    }

    private fun generateToken(subject: String, account: String, role: String, expirePeriod: Duration): String {
        val claims = Jwts.claims().add(mapOf("role" to role, "account" to account)).build()

        val now = Instant.now()
        val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))

        return Jwts.builder()
            .subject(subject)
            .issuer(issuer)
            .issuedAt(Date.from(now))
            .expiration(Date.from(now.plus(expirePeriod)))
            .claims(claims)
            .signWith(key)
            .compact()
    }
}
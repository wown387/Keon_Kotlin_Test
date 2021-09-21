package com.keonkotlin.keon_kotlin.auth

import com.keonkotlin.keon_kotlin.exceptions.ErrorMessageCode
import com.keonkotlin.keon_kotlin.member.UserService
import com.keonkotlin.keon_kotlin.member.model.User
import com.keonkotlin.keon_kotlin.member.view.LogInView
import com.keonkotlin.keon_kotlin.member.view.UserView
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("api")
class AuthController(private val userService: UserService) {

    @PostMapping("register")
    fun register(@RequestBody body: UserView): ResponseEntity<User> {
        val user = User()
        user.name = body.name
        user.email = body.email
        user.password = body.password
        return ResponseEntity.ok(this.userService.save(user))
    }

    @PostMapping("login")
    fun logIn(@RequestBody body: LogInView, response: HttpServletResponse): ResponseEntity<Any> {
        val user = this.userService.findByEmail(body.email)
                ?: return ResponseEntity.badRequest().body(ErrorMessageCode.ERROR.printMessage())

        if (!user.comparePassword(body.password)) {
            return ResponseEntity.badRequest().body(ErrorMessageCode.ERROR.printMessage())
        }

        val issuer = user.id.toString()

        val jwt = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(Date(System.currentTimeMillis() + 3*24*60*1000)) // 3Days
            .signWith(SignatureAlgorithm.HS512, "secret").compact()

        val cookie = Cookie("jwt", jwt)
        // 자세한거 리서치
        cookie.isHttpOnly = true

        response.addCookie(cookie)

        return ResponseEntity.ok("success")
    }

    @GetMapping("user")
    fun user(@CookieValue("jwt") jwt: String?): ResponseEntity<Any> {
        try {
            if(jwt == null) {
                return ResponseEntity.status(401).body(ErrorMessageCode.ERROR.printMessage())
            }

            val body = Jwts.parser().setSigningKey("secret").parseClaimsJws(jwt).body

            return ResponseEntity.ok(this.userService.getById(body.issuer.toLong()))
        } catch (e: Exception) {
            e.printStackTrace()
            return ResponseEntity.badRequest().body(ErrorMessageCode.ERROR.printMessage())
        }
    }

    @PostMapping("logout")
    fun logOut(response: HttpServletResponse): ResponseEntity<Any> {
        var cookie = Cookie("jwt", "")
        // cookie expire
        cookie.maxAge = 0
        response.addCookie(cookie)

        return ResponseEntity.ok(ErrorMessageCode.SUCCESS.printMessage())
    }

}
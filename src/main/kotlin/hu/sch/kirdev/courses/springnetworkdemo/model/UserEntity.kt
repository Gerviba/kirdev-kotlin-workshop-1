package hu.sch.kirdev.courses.springnetworkdemo.model

import hu.gerviba.authsch2springbootstarter.struct.AuthschUser
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

import java.math.BigInteger
import java.security.MessageDigest

@Entity
@Table(name = "users")
data class UserEntity(
        @Id
        @GeneratedValue
        var uid: Long? = null,

        var internalId: String = "",

        var email: String,

        var subjects: String,

        var fullName: String
) : AuthschUser {

    override fun getInternalId(): UUID {
        return UUID.fromString(internalId)
    }

    override fun getId(): Long {
        return uid!!
    }

    fun courseList(): List<String> {
        return subjects.split(",")
    }

    fun emailMd5(): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(email.toByteArray())).toString(16).padStart(32, '0')
    }
}



package hu.sch.kirdev.courses.springnetworkdemo.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
data class UserEntity(
        @Id
        var id: String? = null,

        var email: String,

        var subjects: String,

        var card: String,
)
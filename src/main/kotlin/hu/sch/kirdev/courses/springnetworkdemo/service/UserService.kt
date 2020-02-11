package hu.sch.kirdev.courses.springnetworkdemo.service

import hu.gerviba.authsch2springbootstarter.struct.AuthschUser
import hu.gerviba.authsch2springbootstarter.struct.ProfileDataResponse
import hu.sch.kirdev.courses.springnetworkdemo.model.UserEntity
import hu.sch.kirdev.courses.springnetworkdemo.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional


@Service
@Transactional
class UserService {

    @Autowired
    lateinit var users: UserRepository

    fun getUserById(id: Long): UserEntity {
        return users.findById(id).orElseThrow{ RuntimeException() }
    }

    fun isUserExists(profileDataResponse: ProfileDataResponse): Boolean {
        return users.findByInternalId(profileDataResponse.internalId.toString()).isPresent
    }

    fun loadUser(profileDataResponse: ProfileDataResponse): AuthschUser {
        return users.findByInternalId(profileDataResponse.internalId.toString()).orElseThrow{ RuntimeException() }
    }

    fun createUser(profileDataResponse: ProfileDataResponse): AuthschUser {
        val user = UserEntity(
                fullName = "${profileDataResponse.surname} ${profileDataResponse.givenName}",
                internalId = profileDataResponse.internalId.toString(),
                email = profileDataResponse.mail,
                subjects = profileDataResponse.courses.joinToString(",")
        )
        users.save(user)
        println(user)
        return user
    }

    fun resolveRoles(profileDataResponse: ProfileDataResponse, authschUser: AuthschUser): List<String> {
        return listOf("USER")
    }

}
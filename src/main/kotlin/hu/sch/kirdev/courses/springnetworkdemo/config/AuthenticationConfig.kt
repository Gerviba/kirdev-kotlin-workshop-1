package hu.sch.kirdev.courses.springnetworkdemo.config

import hu.gerviba.authsch2springbootstarter.struct.AuthschLoginLogicSupplier
import hu.gerviba.authsch2springbootstarter.struct.Scope
import hu.sch.kirdev.courses.springnetworkdemo.model.UserEntity
import hu.sch.kirdev.courses.springnetworkdemo.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AuthenticationConfig {

    @Autowired
    lateinit var users: UserService

    @Bean
    fun authschLogicConfig(): AuthschLoginLogicSupplier {
        return AuthschLoginLogicSupplier()
                .setUserClass(UserEntity::class.java)
                .setRequestScopes(
                        Scope.BASIC, Scope.DISPLAY_NAME, Scope.SURNAME,
                        Scope.GIVEN_NAME, Scope.EMAIL,
                        Scope.COURSES)
                .setResolver(users::getUserById)
                .setCreateUser(users::createUser)
                .setLoadUser(users::loadUser)
                .setUserExists(users::isUserExists)
                .setResolveRoles(users::resolveRoles)
    }

}


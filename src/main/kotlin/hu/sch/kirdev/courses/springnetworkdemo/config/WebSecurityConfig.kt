package hu.sch.kirdev.courses.springnetworkdemo.config

import hu.gerviba.authsch2springbootstarter.AuthschConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebSecurityConfig : WebSecurityConfigurerAdapter(), WebMvcConfigurer {

    @Autowired
    lateinit var authschConfig: AuthschConfig

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        authschConfig.registerFilters(http)

        http.authorizeRequests()
                .antMatchers("/", "/login**", "/css/**", "/js/**",
                        "/images/**", "/api/**", "/subject/**", "/subjects").permitAll()
                .antMatchers("/profile", "/courses").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                .and()
                .csrf().disable()
                .cors().disable()
    }

    override fun addArgumentResolvers(argumentResolvers: List<HandlerMethodArgumentResolver>) {
        authschConfig.addArgumentResolvers(argumentResolvers)
    }

}
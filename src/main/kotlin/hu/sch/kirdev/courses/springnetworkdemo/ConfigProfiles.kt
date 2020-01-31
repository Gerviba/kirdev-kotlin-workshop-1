package hu.sch.kirdev.courses.springnetworkdemo

/**
 * List of all available configuration profiles.
 * Always use global compile time constants to avoid spelling errors.
 *
 * To set config profile, use: -Dspring.profiles.active=foo,bar
 * or set the spring.profiles.active property in the application.properties file
 */
object ConfigProfiles {

    const val TEST = "test"
    const val PRODUCTION = "production"
    const val DEV = "dev"

}
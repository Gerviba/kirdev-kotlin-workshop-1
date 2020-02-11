package hu.sch.kirdev.courses.springnetworkdemo.controller

import hu.sch.kirdev.courses.springnetworkdemo.model.UserEntity
import hu.sch.kirdev.courses.springnetworkdemo.service.SubjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class GuestController {

    @Autowired
    lateinit var subjects: SubjectService

    @GetMapping("/")
    fun index() = "index"

    @GetMapping("/subjects")
    fun subjects() = "subjects"

    @GetMapping("/subject/{code}")
    fun subject(@PathVariable code: String, model: Model): String {
        model.addAttribute("subject", subjects.findByCode(code))
        return "subject"
    }

    @GetMapping("/profile")
    fun profile(user: UserEntity, model: Model): String {
        model.addAttribute("user", user)
        model.addAttribute("courses",
                user.courseList()
                        .map { it.replace("BME", "") })
        return "profile"
    }

    @RequestMapping("/403", "/error")
    fun accesssDenied() = "error"

}
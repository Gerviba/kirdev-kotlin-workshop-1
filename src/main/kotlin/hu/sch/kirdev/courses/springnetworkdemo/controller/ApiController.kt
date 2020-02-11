package hu.sch.kirdev.courses.springnetworkdemo.controller

import hu.sch.kirdev.courses.springnetworkdemo.model.SubjectEntity
import hu.sch.kirdev.courses.springnetworkdemo.service.SubjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ApiController {

    @Autowired
    lateinit var subjects: SubjectService

    @GetMapping("/search")
    fun searchApi(@RequestParam keyword: String): List<SubjectEntity> {
        return subjects.searchForSubject(keyword)
    }

}
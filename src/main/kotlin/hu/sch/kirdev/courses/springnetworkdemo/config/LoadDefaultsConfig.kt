package hu.sch.kirdev.courses.springnetworkdemo.config

import hu.sch.kirdev.courses.springnetworkdemo.ConfigProfiles.TEST
import hu.sch.kirdev.courses.springnetworkdemo.model.MajorType
import hu.sch.kirdev.courses.springnetworkdemo.model.SubjectEntity
import hu.sch.kirdev.courses.springnetworkdemo.model.toGraduationLevel
import hu.sch.kirdev.courses.springnetworkdemo.model.toSubjectType
import hu.sch.kirdev.courses.springnetworkdemo.service.SubjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import javax.annotation.PostConstruct

@Profile(TEST)
@Configuration
class LoadDefaultsConfig {

    @Autowired
    lateinit var subjects: SubjectService

    @PostConstruct
    fun init() {
        if (subjects.isNotEmpty())
            return

        TODO("subject.insert all")
    }

}

private class SubjectInputDto(data: List<String>) {
    // create DTO
}
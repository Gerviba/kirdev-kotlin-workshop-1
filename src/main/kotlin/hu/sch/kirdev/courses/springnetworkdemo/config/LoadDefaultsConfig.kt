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

        Files.readAllLines(Path.of("data/inAll.csv"))
                .map { SubjectInputDto(it.split(';')) }
                .map {
                    SubjectEntity(
                        code = it.code,
                        name = it.name,
                        credits = it.credits,
                        requirements = it.requirements,
                        department = if (it.department.isBlank()) "N/A" else it.department,
                        description = it.description,
                        type = it.type,
                        semester = it.semester,
                        major = when (it.major) {
                            "INFO" -> MajorType.IT
                            "VILLANY" -> MajorType.EE
                            else -> MajorType.BOTH
                        },
                        level = it.level.toGraduationLevel()
                    )
                }
                .forEach(subjects::insert)
    }

}

private class SubjectInputDto(data: List<String>) {
    val code            = data[0]
    val name            = data[1]
    val credits         = data[2].toFloat()
    val requirements    = data[3]
    val major: String   = data[4]
    val department      = data[5]
    val level: String   = data[6]
    val type            = data[7].toSubjectType()
    val semester        = data[8].toInt()
    val description     = data[9]
}
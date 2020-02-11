package hu.sch.kirdev.courses.springnetworkdemo.service

import hu.sch.kirdev.courses.springnetworkdemo.model.SubjectEntity
import hu.sch.kirdev.courses.springnetworkdemo.repository.SubjectRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class SubjectService {

    @Autowired
    lateinit var subjects: SubjectRepository

    fun insert(subjectEntity: SubjectEntity) {
        subjects.save(subjectEntity)
    }

    fun isNotEmpty(): Boolean {
        return subjects.count() > 0
    }

    fun searchForSubject(keyword: String): List<SubjectEntity> =
            subjects.findAllByNameLikeOrCodeLikeOrDepartmentLike(
            "%$keyword%",
            "%$keyword%",
            "%$keyword%")
            .take(100)

    fun findByCode(code: String): SubjectEntity = subjects.findByCode(code).orElseThrow()

    fun getAllSubjectsWithCodes(codes: List<String>): List<SubjectEntity> = subjects.findAllByCodeIn(codes)

}
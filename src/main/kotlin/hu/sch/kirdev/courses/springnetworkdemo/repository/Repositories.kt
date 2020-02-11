package hu.sch.kirdev.courses.springnetworkdemo.repository

import hu.sch.kirdev.courses.springnetworkdemo.model.SubjectEntity
import hu.sch.kirdev.courses.springnetworkdemo.model.UserEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : CrudRepository<UserEntity, Long> {
    fun findByInternalId(internalId: String?): Optional<UserEntity>
}

@Repository
interface SubjectRepository : CrudRepository<SubjectEntity, String> {
    fun findAllByNameLikeOrCodeLikeOrDepartmentLike(name: String, code: String, department: String): List<SubjectEntity>
    fun findAllByCodeIn(codes: List<String>): List<SubjectEntity>
    fun findByCode(code: String): Optional<SubjectEntity>
}


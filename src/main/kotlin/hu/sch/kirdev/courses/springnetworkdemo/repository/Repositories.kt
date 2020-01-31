package hu.sch.kirdev.courses.springnetworkdemo.repository

import hu.sch.kirdev.courses.springnetworkdemo.model.SubjectEntity
import hu.sch.kirdev.courses.springnetworkdemo.model.UserEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<UserEntity, String>

@Repository
interface SubjectRepository : CrudRepository<SubjectEntity, String>


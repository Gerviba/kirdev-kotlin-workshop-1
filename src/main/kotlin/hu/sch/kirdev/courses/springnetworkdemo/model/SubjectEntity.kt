package hu.sch.kirdev.courses.springnetworkdemo.model

import javax.persistence.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "subjects")
data class SubjectEntity (
        @Id
        val code: String,

        @NotBlank
        val name: String,

        @Min(0)
        val credits: Float,

        @Min(0)
        val semester: Int,

        val requirements: String,

        @Column(columnDefinition = "TEXT")
        val description: String,

        val department: String,

        @Enumerated(EnumType.STRING)
        val major: MajorType,

        @Enumerated(EnumType.STRING)
        val level: GraduationLevel,

        @Enumerated(EnumType.STRING)
        val type: SubjectType
)

enum class SubjectType {
    /**
     * Translation: Mintatanterv szerinti
     */
    CORE,
    /**
     * Translation: Szabadon választható
     */
    OPTIONAL,
    /**
     * Translation: Kötelezően válaszható
     */
    SELECTABLE
}

enum class GraduationLevel { BSC, MSC, PHD, NOT_SPECIFIED }

enum class MajorType { IT, EE, BOTH }

// TODO: toSubjectType()

// TODO: toGraduationLevel()
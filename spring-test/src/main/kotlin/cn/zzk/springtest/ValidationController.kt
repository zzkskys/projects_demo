package cn.zzk.springtest

import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty

/**
 *
 * Created Date : 2022/05/09
 * @author zzk
 */
@RestController
@RequestMapping("/validation")
@Validated
class ValidationController {

    @PostMapping("/people")
    fun addPerson(@Valid @RequestBody person: Person) {

    }
}

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MissingKotlinParameterException::class)
    fun handle(e: MissingKotlinParameterException): String {
        val paramName = e.parameter.name
        return "$paramName 不能为 null"
    }
}

data class Person(
    @field:NotEmpty
    val id: String,

    @field:Min(2)
    val name: String
)
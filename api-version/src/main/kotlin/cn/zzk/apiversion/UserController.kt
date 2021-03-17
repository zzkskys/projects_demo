package cn.zzk.apiversion

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/users")
@RestController
@Api(description = "用户")
class UserController {


    @PostMapping(name = "a", headers = ["v=1"])
    fun addUser(name: String): String {
        return "执行添加用户,版本 : 1 , name : $name"
    }

    @PostMapping(headers = ["v=2"])
    fun addUserV2(name: String, age: Int): String {
        return "执行添加用户 , 版本 : 2 ...... name :$name,age:$age"
    }

}

@RequestMapping("/persons")
@RestController
@Api(description = "人员")
class PersonController {

    @ApiOperation("人员添加版本2")
    @PostMapping(params = ["v=2"])
    fun addPersonV2(name: String, age: Int): String {
        return "执行人员添加 , 版本 : 2 ...... name :$name,age:$age"
    }

    @ApiOperation("人员添加版本1")
    @PostMapping(params = ["v=1"])
    fun addPerson(name: String): String {
        return "执行添加人员 , 版本 : 1 ..... name:$name"
    }
}

@RestController
@RequestMapping("/students")
@Api(tags = ["学生"])
class StudentController {

    @Deprecated("被废弃", ReplaceWith("v2"))
    @PostMapping("/v1")
    fun addStudents(name: String?): String {
        return "版本1,添加人员"
    }

    @PostMapping("/v2")
    fun addStudentsV2(name: String?): String {
        return "版本2,添加人员"
    }
}
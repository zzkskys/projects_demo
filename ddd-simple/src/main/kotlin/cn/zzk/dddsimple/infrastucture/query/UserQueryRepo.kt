package cn.zzk.dddsimple.infrastucture.query

import cn.zzk.dddsimple.domain.User

/**
 * 用户查询接口
 */
interface UserQueryRepo : CustomQuery {

    fun findAll(): List<User>


    fun findAllDTO(): List<UserDTO>

}

interface CustomQuery {

    fun statisticUsers(): List<User>
}
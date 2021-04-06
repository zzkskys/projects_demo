package cn.zzk.dddsimple.infrastucture.query

import cn.zzk.dddsimple.domain.User

/**
 * 用户查询接口
 */
interface UserQueryRepo {

    fun findAll(): List<User>


    fun findAllDTO(): List<UserDTO>

}

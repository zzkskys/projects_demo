package cn.zzk.dddsimple.domain

/**
 * 用户创建事件
 */
open class UserSaved(
    val user: User,
    val time: Long = System.currentTimeMillis()
)
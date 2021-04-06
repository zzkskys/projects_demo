package cn.zzk.dddsimple.application

import cn.zzk.dddsimple.domain.UserSaved

interface UserSavedProducer {

    fun userSavedSend(event: UserSaved)
}
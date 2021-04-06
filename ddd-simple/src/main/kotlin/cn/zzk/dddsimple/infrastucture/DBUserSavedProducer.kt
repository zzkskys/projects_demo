package cn.zzk.dddsimple.infrastucture

import cn.zzk.dddsimple.application.UserSavedProducer
import cn.zzk.dddsimple.domain.UserSaved
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

/**
 * 存储用户更改事件,并将更改事件传递至其他系统
 */
@Component
class DBUserSavedProducer : UserSavedProducer {

    // 可以实现事件存储库,将相应的事件存储至事件存储库并发送

    /**
     * 向远程发送用户更新消息
     */
    override fun userSavedSend(event: UserSaved) {

    }

    /**
     * 真正发送用户更新消息的方式
     */
    @Scheduled(fixedRate = 30_000)
    fun remoteAddUsers() {

    }
}

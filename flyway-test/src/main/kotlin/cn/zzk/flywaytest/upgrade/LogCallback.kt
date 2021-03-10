package cn.zzk.flywaytest.upgrade

import org.flywaydb.core.api.callback.Callback
import org.flywaydb.core.api.callback.Context
import org.flywaydb.core.api.callback.Event
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component


@Component
class LogCallback : Callback {

    companion object {
        private val log = LoggerFactory.getLogger(LogCallback::class.java)
    }

    /**
     * 回调的执行时间
     */
    override fun supports(event: Event, context: Context): Boolean {
        return event == Event.AFTER_EACH_MIGRATE
    }

    /**
     * 回调是否再事务进行，默认选是
     */
    override fun canHandleInTransaction(event: Event, context: Context): Boolean {
        return true
    }

    /**
     * 回调的具体处理逻辑
     */
    override fun handle(event: Event, context: Context) {
        log.info("执行了一次回调")
    }

    /**
     * 回调的名称 : flyway 会对回调名称顺序执行
     */
    override fun getCallbackName(): String {
        return "LogCallback"
    }

}
package cn.zzk.dddsimple.domain

import org.springframework.data.jpa.repository.JpaRepository

interface MessageRepo : JpaRepository<Message, String> {

}
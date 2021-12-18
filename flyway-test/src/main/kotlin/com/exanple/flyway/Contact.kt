package com.exanple.flyway

import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

/**
 *
 * Created Date : 2021/12/17
 * @author zzk
 */
@Entity
class Contact(
    @Id
    var phone: String = "",

    var email: String = "",

    @ManyToOne
    var user: User? = null
)

interface ContactRepo : JpaRepository<Contact, String>
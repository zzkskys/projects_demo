package com.exanple.flyway.upgrade

import org.flywaydb.core.api.migration.BaseJavaMigration
import org.flywaydb.core.api.migration.Context
import org.springframework.stereotype.Component

@Component
class V2__Upgrade : BaseJavaMigration() {
    override fun migrate(context: Context) {
        val statement = context.connection.createStatement()
        statement.execute("""
           create table if not exists person(
                id varchar(36) primary key not null,
                name varchar(10) not null 
           )
        """)
    }
}
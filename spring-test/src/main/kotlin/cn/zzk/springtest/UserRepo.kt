package cn.zzk.springtest

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

interface UserRepo : JpaRepository<User, String>, CustomUserQuery {

    fun existsByName(name: String): Boolean


}

interface CustomUserQuery {


    /**
     * 如何直接查询并返回 DTO
     */
    fun findAllUserDTOs(): List<UserDTO>
}

@Repository
class CustomUserQueryImpl(
    val jdbcTemplate: NamedParameterJdbcTemplate
) : CustomUserQuery {
    override fun findAllUserDTOs(): List<UserDTO> {
        val sql =
            "select user.id,user.name,unit.id as unitId ,unit.name as unitName from user left join organ_unit unit on user.organ_unit_id = unit.id"
        return jdbcTemplate.query(sql, BeanPropertyRowMapper(UserDTO::class.java))
    }

}


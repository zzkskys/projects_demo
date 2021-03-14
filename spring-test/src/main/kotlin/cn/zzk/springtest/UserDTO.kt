package cn.zzk.springtest

class UserDTO {

    var id: String? = null

    var name: String? = null

    var unitId: String? = null

    var unitName: String? = null

    fun getOrganUnit(): OrganUnit? {
        if (unitId != null) {
            return OrganUnit(name = unitName!!, id = unitId!!)
        }
        return null
    }
}


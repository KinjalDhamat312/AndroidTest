package com.demo.androiddemo.data.local.model

import androidx.annotation.Keep
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.demo.androiddemo.data.remote.result.BaseResult

@Keep
data class ResUser(
    val results: List<UserData>? = null,
    val info: Info? = null
) : BaseResult()

@Keep
@Entity(tableName = "User")
data class UserData(


    @Embedded(prefix = "id_") val id: Id,
    val nat: String? = null,
    val gender: String? = null,
    val phone: String? = null,
    @Embedded(prefix = "dob") val dob: Dob? = null,
    @Embedded(prefix = "name_") val name: Name? = null,
    @Embedded(prefix = "registration_") val registered: Registered? = null,
    @Embedded val location: Location? = null,
    @Embedded val login: Login? = null,
    @PrimaryKey
    val cell: String = "",
    val email: String? = null,
    @Embedded val picture: Picture? = null
)


@Keep
data class Location(
    val country: String? = null,
    val city: String? = null,
    @Embedded(prefix = "street_") val street: Street? = null,
    @Embedded val timezone: Timezone? = null,
    val postcode: String? = null,
    @Embedded val coordinates: Coordinates? = null,
    val state: String? = null
)

@Keep
data class Timezone(
    val offset: String? = null,
    val description: String? = null
)

@Keep
data class Coordinates(
    val latitude: String? = null,
    val longitude: String? = null
)

@Keep
 class Id( var name: String? = null,
           var value: String? = null)

@Keep
data class Info(
    val seed: String? = null,
    val page: Int? = null,
    val results: Int? = null,
    val version: String? = null
)

@Keep
data class Dob(
    val date: String? = null,
    val age: Int? = null
)

@Keep
data class Street(
    val number: Int? = null,
    val name: String? = null
)


@Keep
data class Picture(
    val thumbnail: String? = null,
    val large: String? = null,
    val medium: String? = null
)

@Keep
data class Login(
    val sha1: String? = null,
    val password: String? = null,
    val salt: String? = null,
    val sha256: String? = null,
    val uuid: String? = null,
    val username: String? = null,
    val md5: String? = null
)

@Keep
data class Name(
    val last: String? = null,
    val title: String? = null,
    val first: String? = null
)

@Keep
data class Registered(
    val date: String? = null,
    val age: Int? = null
)


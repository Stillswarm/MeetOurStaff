package com.example.meetourstaff

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Person(
    @StringRes val firstName: Int,
    @StringRes val lastName: Int,
    val age: Int,
    @DrawableRes val profilePic: Int
)

object DataSource {

    val people = listOf(
        Person(R.string.rameshbabu, R.string.praggnananda, 19, R.drawable.pragg),
        Person(R.string.hikaru, R.string.nakamura, 29, R.drawable.hikaru),
        Person( R.string.dommaraju, R.string.gukesh,18, R.drawable.gukesh),
        Person(R.string.vidit, R.string.gujrathi, 20, R.drawable.vidit),
        Person(R.string.ian, R.string.nepomniatchi,30, R.drawable.nepo),
        Person(R.string.ding, R.string.liren, 25, R.drawable.ding),
        Person(R.string.magnus, R.string.carlsen, 28, R.drawable.magnus),
        Person(R.string.fabiano, R.string.caruana, 25, R.drawable.fabi)
    )

    val NO_OF_STAFF = people.size
}
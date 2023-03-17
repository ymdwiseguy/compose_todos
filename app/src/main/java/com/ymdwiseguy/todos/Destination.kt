package com.ymdwiseguy.todos

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class Destination : Parcelable {

    @Parcelize
    object Start : Destination()

    @Parcelize
    object Login : Destination()

    @Parcelize
    object Todos : Destination()
}

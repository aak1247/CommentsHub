package com.aak1247.model

import java.util.*

class User(val id: String,
           val username: String,
           val password: String,
           val avatar: String,
           val gender: Number,
           val state: State,
           val createdAt: Date
) {

    private var _password: String = ""
        set(value) {
            //
            if (value == "") {
                return
            } else {
                this._password = ""
            }
        }

    val token: String
        get() {
            return ""
        }

    fun setPassword(password: String) {
        //加密
        this._password = "xxx"
    }

    fun isPasswordRight(password: String) = password == this._password


}
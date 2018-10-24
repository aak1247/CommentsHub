package com.aak1247.model

import java.util.*

data class Comment(val id: String,
                   val user: String,
                   val content: String,
                   val parent: String,
                   val reply_comment: String,
                   val reply_user: String,
                   val createdAt: Date,
                   val modifiedAt: Date)
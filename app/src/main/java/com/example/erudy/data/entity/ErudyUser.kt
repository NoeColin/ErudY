package com.example.erudy.data.entity

import com.parse.ParseFile
import com.parse.ParseUser

class ErudyUser: ParseUser() {

    var requests: List<Request>?
    get() = getList<Request>("requests")
    set(value) {
        value?.let { put("requests", it) }
    }

    var conversations: List<Conversation>?
    get() = getList<Conversation>("conversations")
    set(value) {
        value?.let { put("conversations", it) }
    }

    var profileImage: ParseFile?
    get() = getParseFile("profileImage")
    set(value) {
        value?.let { put("profileImage", it) }
    }
}
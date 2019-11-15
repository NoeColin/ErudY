package com.erudy.data.entity

import com.parse.ParseClassName
import com.parse.ParseObject

@ParseClassName("Request")
class Request: ParseObject() {

    var title: String?
    get() = getString("title")
    set(value) {
        value?.let { put("title", it) }
    }

    var description: String?
    get() = getString("description")
    set(value) {
        value?.let { put("description", it) }
    }

    var owner: ErudyUser?
    get() = getParseUser("owner") as ErudyUser
    set(value) {
        value?.let { put("owner", it) }
    }
    
    var conversations: List<Conversation>?
    get() = getList<Conversation>("conversations")
    set(value) {
        value?.let { put("conversations", it) }
    }
}
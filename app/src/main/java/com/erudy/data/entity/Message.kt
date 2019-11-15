package com.erudy.data.entity

import com.parse.ParseClassName
import com.parse.ParseObject

@ParseClassName("Message")
class Message: ParseObject() {

    var content: String?
    get() = getString("content")
    set(value) {
        value?.let { put("content", it) }
    }

    var conversation: Conversation?
    get() = getParseObject("conversation") as Conversation
    set(value) {
        value?.let { put("conversation", it) }
    }

    var writer: ErudyUser?
    get() = getParseUser("writer") as ErudyUser
    set(value) {
        value?.let { put("writer", it) }
    }
}
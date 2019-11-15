package com.example.erudy.data.entity

import com.parse.ParseClassName
import com.parse.ParseObject

@ParseClassName("Conversation")
class Conversation: ParseObject() {

    var user1: ErudyUser?
    get() = getParseUser("user1") as ErudyUser
    set(value) {
        value?.let { put("user1", it) }
    }

    var user2: ErudyUser?
    get() = getParseUser("user2") as ErudyUser
    set(value) {
        value?.let { put("user2", it) }
    }

    var messages: List<Message>?
    get() = getList<Message>("messages")
    set(value) {
        value?.let { put("messages", it) }
    }

    var request: Request?
    get() = get("request") as Request
    set(value) {
        value?.let { put("request", it) }
    }
}
package com.erudy.data.entity

import com.parse.ParseClassName
import com.parse.ParseFile
import com.parse.ParseUser

@ParseClassName("_User")
class ErudyUser: ParseUser() {

    var firstName: String?
    get() = getString("firstName")
    set(value) {
        value?.let { put("firstName", it) }
    }

    var lastName: String?
    get() = getString("lastName")
    set(value) {
        value?.let { put("lastName", it) }
    }

    val fullName: String
        get() = "$firstName $lastName"

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
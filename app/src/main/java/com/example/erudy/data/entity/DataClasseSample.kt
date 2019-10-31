package com.example.erudy.data.entity

import com.parse.ParseClassName
import com.parse.ParseObject

@ParseClassName("DataClasseSample")
class DataClasseSample: ParseObject() {

    var displayName : String?
        get() = getString("displayName")
        set(value) {
            value?.let { put("displayName", it) }
        }
}
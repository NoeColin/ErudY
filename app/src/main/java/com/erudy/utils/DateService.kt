package com.erudy.utils

import java.text.SimpleDateFormat
import java.util.*

class DateService {
    companion object {
        val instance = DateService()
    }

    fun toDateTime(date : Date) : String {
        val pattern = "dd/MM/yyyy HH:mm"
        var simpleDateFormat = SimpleDateFormat(pattern)
        var formattedDate = simpleDateFormat.format(date)
        return formattedDate.toString()
    }

    fun toDate(date : Date) : String {
        val pattern = "dd/MM/yyyy"
        var simpleDateFormat = SimpleDateFormat(pattern)
        var formattedDate = simpleDateFormat.format(date)
        return formattedDate.toString()
    }
}
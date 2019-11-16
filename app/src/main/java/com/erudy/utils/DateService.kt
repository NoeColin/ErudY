package com.erudy.utils

import java.text.SimpleDateFormat
import java.util.*

class DateService {
    companion object {
        val instance = DateService()
    }

    //TODO check si les dates sont null
    //TODO transformer le texte en brute en resources values string
    fun toDateTime(date: Date): String {
        var formattedDate = toDate(date) + " " + toTime(date)
        return formattedDate
    }

    fun toDate(date: Date): String {
        val pattern = "dd MMM yyyy"
        var calendar = Calendar.getInstance()
        calendar.setTime(date)

        if (isToday(calendar)) {
            return "Aujourd'hui"
        } else if (isYesterday(calendar)) {
            return "Hier"
        } else {
            var simpleDateFormat = SimpleDateFormat(pattern)
            var formattedDate = "le " + simpleDateFormat.format(date)
            return formattedDate
        }
    }

    fun toTime(date: Date): String {
        val pattern = "HH:mm"
        var simpleDateFormat = SimpleDateFormat(pattern)
        var formattedDate = "à " + simpleDateFormat.format(date)
        return formattedDate
    }

    fun isToday(date1: Calendar): Boolean {
        //TODO check si les dates sont null

        var date2 =  Calendar.getInstance()

        return (date1.get(Calendar.ERA) == date2.get(Calendar.ERA) &&
                date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR) &&
                date1.get(Calendar.DAY_OF_YEAR) == date2.get(Calendar.DAY_OF_YEAR))
    }

    fun isYesterday(date1: Calendar): Boolean {
        //TODO check si les dates sont null
        var date2 =  Calendar.getInstance()
        return (date1.get(Calendar.ERA) == date2.get(Calendar.ERA) &&
                date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR) &&
                date1.get(Calendar.DAY_OF_YEAR) == date2.get(Calendar.DAY_OF_YEAR) - 1)
    }
}
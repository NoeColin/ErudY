package com.erudy.utils

import com.erudy.R
import java.text.SimpleDateFormat
import java.util.*

class DateService {
    companion object {
        val instance = DateService()
    }

    //TODO check si les dates sont null
    //TODO transformer le texte en brute en resources values string
    fun toDateTime(date: Date): String {
        val formattedDate = toDate(date) + " " + toTime(date)
        return formattedDate

        /*var pattern = "dd/MM/yyyy HH:mm"
        var calendar = Calendar.getInstance()
        calendar.setTime(date)

        if (isToday(calendar)) {
            return "${R.string.today} " + toTime(date)
        } else if (isYesterday(calendar)) {
            return "${R.string.yesterday} " + toTime(date)
        } else {
            var simpleDateFormat = SimpleDateFormat(pattern)
            var formattedDate = "${R.string.the} ${simpleDateFormat.format(date)}"
            return formattedDate
        }*/
    }

    fun toDate(date: Date): String {
        var pattern = "dd/MM/yyyy"
        /*var calendar = Calendar.getInstance()
        calendar.setTime(date)

        if (isToday(calendar)) {
            return "${R.string.today}"
        } else if (isYesterday(calendar)) {
            return "${R.string.yesterday}"
        } else {*/
            var simpleDateFormat = SimpleDateFormat(pattern)
            var formattedDate = simpleDateFormat.format(date)
            return formattedDate
        //}
    }

    fun toTime(date: Date): String {
        var pattern = "HH:mm"
        var simpleDateFormat = SimpleDateFormat(pattern)
        var formattedDate = simpleDateFormat.format(date)
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
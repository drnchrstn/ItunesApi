package com.fourello.itunesapi.Utils

import java.text.SimpleDateFormat
import java.util.*

class DateTimeUtils {

    companion object{

        val utc = TimeZone.getTimeZone("UTC")
        val sourceFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val targetFormat = SimpleDateFormat("yyyy-MM-dd")



        fun convertMillsToTime(mills: Long): String{
            val min = mills / 1000/ 60
            val seconds = mills / 1000 % 60
            

            return "$min:$seconds"


        }

        fun getReleaseDate(releaseDate: String?): String?{
            sourceFormat.timeZone = utc
            var converted = sourceFormat.parse(releaseDate)
            var formatted = targetFormat.format(converted)

            var month = monthToString(formatted.substringAfter("-").substringBefore("-").toInt())
            var day = formatted.substringAfterLast("-")
            var year = formatted.substringBefore("-")

            return "$month $day $year"

        }


        fun monthToString(monthYear: Int): String?{
            var month: String? =null
            if (!monthYear.equals(0)){
                when(monthYear){
                    1-> month = "January"
                    2->month = "Febuary"
                    3-> month = "March"
                    4-> month = "April"
                    5-> month = "May"
                    6-> month = "June"
                    7-> month ="July"
                    8-> month = "August"
                    9-> month = "September"
                    10-> month = "October"
                    11-> month ="November"
                    12-> month = "December"
                }
            }
            return  month
        }


    }


}
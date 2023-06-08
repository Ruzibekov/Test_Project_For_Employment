package uz.ruzibekov.test.utils

import java.text.DateFormatSymbols
import java.util.Calendar

object DateFactory {

    fun getTodayDateAndTime(): String {
        val calendar = Calendar.getInstance()

        val year = calendar.get(Calendar.YEAR)
        val monthNum = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val month = DateFormatSymbols().months[monthNum]

        return "$day $month, $year"
    }
}
package com.gabez.app_database.room_database.converters

import androidx.room.TypeConverter

class Converters {

    private val LIST_DELIMITER: String = ";;;"

    @TypeConverter
    fun fromStringListToString(list: List<String>): String {
        var string: String = ""

        list.forEach { item ->
            string = string + item + LIST_DELIMITER
        }

        return string
    }

    @TypeConverter
    fun fromStringToStringList(string: String): List<String> {
        return string.split(LIST_DELIMITER)
    }

    @TypeConverter
    fun fromIntListToString(list: List<Int>): String {
        var string: String = ""

        list.forEach { item ->
            string = string + item + LIST_DELIMITER
        }

        return string
    }

    @TypeConverter
    fun fromStringToIntList(string: String): List<Int> {
        val stringList: List<String> = string.split(LIST_DELIMITER)
        val intList: ArrayList<Int> = ArrayList()

        stringList.forEach { item ->
            intList.add(item.toInt())
        }

        return intList
    }
}
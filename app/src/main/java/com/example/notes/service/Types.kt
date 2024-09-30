package com.example.notes.service

data class UVResponse(
    val result: UVResult
)

data class UVResult(
    val uv_max: Double,
    val uv_max_time: String,
//    val sun_info: SunInfo
)

//data class SunInfo(
//    val sun_times: SunTimes,
//)
//
//data class SunTimes(
//    val solarNoon: String,
//    val nadir: String,
//    val sunrise: String,
//    val sunset: String,
//    val sunriseEnd: String,
//    val sunsetStart: String,
//    val dawn: String,
//    val dusk: String,
//    val nauticalDawn: String,
//    val nauticalDusk: String,
//    val nightEnd: String,
//    val night: String,
//    val goldenHourEnd: String,
//    val goldenHour: String
//)


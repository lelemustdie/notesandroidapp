package com.example.notes.service

data class UVResponse(
    val result: UVResult
)

data class UVResult(
    val uv_max: Double,
    val uv_max_time: String,
)


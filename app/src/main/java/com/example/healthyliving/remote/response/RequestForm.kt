package com.example.healthyliving.remote.response

data class RequestForm(
    var jenis_kelamin: String,
    var usia: Int,
    var tinggi_badan: Int,
    var berat_badan: Int,
    var aktivitas_fisik: String,
    var token: String
)

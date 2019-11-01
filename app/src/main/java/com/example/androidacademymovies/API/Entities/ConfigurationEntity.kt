package com.example.androidacademymovies.API.Entities

data class ConfigurationEntity (
    var secure_base_url: String,
    var backdrop_sizes: List<String>,
    var poster_sizes: List<String>

)

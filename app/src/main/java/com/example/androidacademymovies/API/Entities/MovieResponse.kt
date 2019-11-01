package com.example.androidacademymovies.API.Entities

data class MovieResponse (
    var page: Int,
    var results: List<Movie>,
    var total_results: Int,
    var total_pages: Int
)




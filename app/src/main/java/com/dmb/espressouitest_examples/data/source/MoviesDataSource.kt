package com.dmb.espressouitest_examples.data.source

import com.dmb.espressouitest_examples.data.Movie

interface MoviesDataSource {

    fun getMovie(movieId: Int): Movie?

    fun getMovies(): List<Movie>
}
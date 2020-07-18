package com.dmb.espressouitest_examples.factory

import androidx.fragment.app.FragmentFactory
import com.dmb.espressouitest_examples.ui.movie.DirectorsFragment
import com.dmb.espressouitest_examples.ui.movie.MovieDetailFragment
import com.dmb.espressouitest_examples.ui.movie.StarActorsFragment

class MovieFragmentFactory : FragmentFactory(){

    private val TAG: String = "AppDebug"

    override fun instantiate(classLoader: ClassLoader, className: String) =

        when(className){

            MovieDetailFragment::class.java.name -> {
                MovieDetailFragment()
            }

            DirectorsFragment::class.java.name -> {
                DirectorsFragment()
            }

            StarActorsFragment::class.java.name -> {
                StarActorsFragment()
            }

            else -> {
                super.instantiate(classLoader, className)
            }
        }
}
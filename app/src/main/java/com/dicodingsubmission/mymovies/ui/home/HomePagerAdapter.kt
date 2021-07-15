package com.dicodingsubmission.mymovies.ui.home

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dicodingsubmission.mymovies.R
import com.dicodingsubmission.mymovies.ui.home.movies.MoviesFragment
import com.dicodingsubmission.mymovies.ui.home.tv_shows.TVShowsFragment

class HomePagerAdapter(private val context: Fragment, fm: FragmentManager):FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object{
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.tab_home_1, R.string.tab_home_2)
    }

    override fun getCount(): Int = TAB_TITLES.size

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> TVShowsFragment()
            1 -> MoviesFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence = context.resources.getString(TAB_TITLES[position])

}
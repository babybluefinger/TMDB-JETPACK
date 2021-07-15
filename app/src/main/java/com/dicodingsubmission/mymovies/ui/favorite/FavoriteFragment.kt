package com.dicodingsubmission.mymovies.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicodingsubmission.mymovies.R
import com.dicodingsubmission.mymovies.databinding.FragmentFavoriteBinding
import com.dicodingsubmission.mymovies.databinding.FragmentHomeBinding
import com.dicodingsubmission.mymovies.ui.home.HomePagerAdapter

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            val favoritePagerAdapter = FavoritePagerAdapter(this@FavoriteFragment, childFragmentManager)
            viewPager.adapter = favoritePagerAdapter
            tabs.setupWithViewPager(viewPager)
        }

    }
}
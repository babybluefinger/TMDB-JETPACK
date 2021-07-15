package com.dicodingsubmission.mymovies.ui

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.dicodingsubmission.mymovies.R
import com.dicodingsubmission.mymovies.ui.favorite.FavoriteFragment
import com.dicodingsubmission.mymovies.ui.home.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        navView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    val fragment = HomeFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_favorite -> {
                    val fragment = FavoriteFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
        navView.setupWithNavController(navController)
    }

}
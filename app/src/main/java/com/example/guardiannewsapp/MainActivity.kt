 package com.example.guardiannewsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.guardiannewsapp.ui.view.FavNewsFragment
import com.example.guardiannewsapp.ui.view.HomePageFragment

 class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        val fragment = HomePageFragment()

        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerView, fragment)
            .commit()
    }
}
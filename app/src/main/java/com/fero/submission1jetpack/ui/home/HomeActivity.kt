package com.fero.submission1jetpack.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import com.fero.submission1jetpack.R
import com.fero.submission1jetpack.databinding.ActivityHomeBinding
import com.fero.submission1jetpack.ui.AboutActivity
import com.fero.submission1jetpack.ui.favorite.FavoriteActivity
import dagger.android.support.DaggerAppCompatActivity


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)

        supportActionBar?.elevation  = 0f

    }

    //setting menu in action bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // actions on click menu items
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
//            R.id.action_about -> {
//                val moveIntent = Intent(this, AboutActivity::class.java)
//                startActivity(moveIntent)
//            }
            R.id.action_favorite -> {
                val moveIntent = Intent(this, FavoriteActivity::class.java)
                startActivity(moveIntent)
            }

        }
    }
}
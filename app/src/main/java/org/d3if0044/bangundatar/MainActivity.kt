package org.d3if0044.bangundatar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

const val KEY_REVENUE = "revenue_key"
const val KEY_BANGUN_DATAR = "bangun_datar_key"

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private var revenue = 0
    private var bangundatar = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)

        if (savedInstanceState != null) {
            revenue = savedInstanceState.getInt(KEY_REVENUE, 0)
            bangundatar = savedInstanceState.getInt(KEY_BANGUN_DATAR, 0)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_REVENUE, revenue)
        outState.putInt(KEY_BANGUN_DATAR, bangundatar)
    }
}
package com.example.android.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.android.navigation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        actionBar?.title = "Example 1"


        // initilize the drawerLayout from the binding variable
        drawerLayout = binding.drawerLayout

        // using KTX,
        val navController = this.findNavController(R.id.myNavHostFragment)

        // link NavController to ActionBar
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        // appBarConfiguration with the navController.graph and drawerLayout
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)

        // lock and unlock the drawerlayout as per NavDestnation matches the startDestination of
        // of our graph and this is doing with the help of lambda expression
        // prevent nav gesture if not on start destination
        navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, args: Bundle? ->
            if(nd.id == nc.graph.startDestination) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            } else
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        }

        // link the Navigation UI up to navigation view
        NavigationUI.setupWithNavController(binding.navView, navController)
    }

    // tp handle the navigateUp action from our Activity
    override fun onSupportNavigateUp(): Boolean {
        // using KTX,
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }
}

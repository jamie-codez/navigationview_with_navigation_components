package com.code.jamie.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var navigationController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var navigationListener: NavController.OnDestinationChangedListener
    lateinit var listener:NavigationView.OnNavigationItemSelectedListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigationController = findNavController(R.id.frag_container)
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_layout)
        appBarConfiguration = AppBarConfiguration(navigationController.graph,drawerLayout)
        navigationView.setupWithNavController(navigationController)
        setupActionBarWithNavController(navigationController,drawerLayout)
        navigationListener = NavController.OnDestinationChangedListener{controller, destination, arguments ->
            when (destination.id) {
                R.id.firstFragment -> supportActionBar!!.title="First Fragment"
                R.id.secondFragment -> supportActionBar!!.title="Second Fragment"
                R.id.thirdFragment -> supportActionBar!!.title="Third Fragment"
                R.id.fourthFragment -> supportActionBar!!.title="Fourth Fragment"
            }
        }
        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.rate_us -> {Snackbar.make(drawerLayout,"Rate us",Snackbar.LENGTH_LONG).apply {
                    Snackbar.ANIMATION_MODE_FADE
                    show()
                }
                    return@setNavigationItemSelectedListener true
                }
                R.id.send_feed_back->{Snackbar.make(drawerLayout,"Send feedback",Snackbar.LENGTH_LONG).apply {
                    Snackbar.ANIMATION_MODE_FADE
                    show()
                }
                    return@setNavigationItemSelectedListener true
                }
                else -> {Toast.makeText(this,"Toast",Toast.LENGTH_LONG).show()
                    return@setNavigationItemSelectedListener true
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        navigationController.addOnDestinationChangedListener(navigationListener)
    }

    override fun onPause() {
        super.onPause()
        navigationController.removeOnDestinationChangedListener(navigationListener)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.frag_container)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
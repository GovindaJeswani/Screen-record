
package com.ibashkimi.screenrecorder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.ibashkimi.screenrecorder.settings.PreferenceHelper

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            onFirstCreate()
        }

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.main_nav_host_fragment)

        findViewById<Toolbar?>(R.id.toolbar)?.let {
            setSupportActionBar(it)
            val appBarConfiguration = AppBarConfiguration(
                setOf(R.id.home, R.id.navigation_dialog, R.id.more_settings_dialog)
            )
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        }
    }

    /**
     *
     * Called the first time the activity is created.
     */
    private fun onFirstCreate() {
        PreferenceHelper(this).apply {
            // Apply theme before onCreate
            applyNightMode(nightMode)
            initIfFirstTimeAnd {
                createNotificationChannels()
            }
        }
    }

    override fun onSupportNavigateUp() = navController.navigateUp()

    companion object {
        const val ACTION_TOGGLE_RECORDING = "com.ibashkimi.screenrecorder.TOGGLE_RECORDING"
    }
}
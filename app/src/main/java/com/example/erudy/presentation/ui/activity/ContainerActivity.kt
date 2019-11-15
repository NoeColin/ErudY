package com.example.erudy.presentation.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.erudy.R
import com.example.erudy.data.entity.ErudyUser
import com.google.android.material.navigation.NavigationView
import com.parse.ParseUser
import android.graphics.drawable.ColorDrawable
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Build
import androidx.annotation.RequiresApi


class ContainerActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)



        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_profile, R.id.nav_conversation
            ), drawerLayout
        )

        val headerView = navView.getHeaderView(0)
        val headerTitleView = headerView.findViewById<TextView>(R.id.nav_header_title)
        val headerSubitleView = headerView.findViewById<TextView>(R.id.nav_header_subtitle)

        val erudyUser = ParseUser.getCurrentUser() as ErudyUser
        headerTitleView.text = erudyUser.fullName
        headerSubitleView.text = erudyUser.email

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.container, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun gotToRequestDetail(idRequest: String) {
        var intent = Intent(this, RequestDetailActivity::class.java)
        intent.putExtra("idRequest", idRequest)
        startActivity(intent)
    }

    fun goToEditProfile() {
        val intent = Intent(this, EditProfileActivity::class.java)
        startActivity(intent)
}
}

package com.example.finbuddy

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        val sipButton = findViewById<Button>(R.id.sip_button)
        val savings = findViewById<Button>(R.id.savings_button)
        val emiButton = findViewById<Button>(R.id.emi_button)
        val debtButton = findViewById<Button>(R.id.debt_button)
        val learningHub = findViewById<Button>(R.id.learning)

        sipButton.setOnClickListener {
            val intent = Intent(this, SipActivity::class.java)
            startActivity(intent)
            finish()
        }
        savings.setOnClickListener {
            val intent = Intent(this, SavingsActivity::class.java)
            startActivity(intent)
            finish()
        }
        emiButton.setOnClickListener {
            val intent = Intent(this, EmiActivity::class.java)
            startActivity(intent)
            finish()
        }
        debtButton.setOnClickListener {
            val intent = Intent(this, DebtActivity::class.java)
            startActivity(intent)
            finish()
        }
        learningHub.setOnClickListener {
            val intent = Intent(this, LearningActivity::class.java)
            startActivity(intent)
            finish()
        }

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Setup ActionBarDrawerToggle for sidebar
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Handle Bottom Navigation
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnItemSelectedListener  { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_news -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bloomberg.com/asia"))
                    startActivity(intent)
                    true
                }
                R.id.nav_stocks -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.nseindia.com/"))
                    startActivity(intent)
                    true
                }
                R.id.nav_profile -> {
                    Log.d("BottomNav", "Profile clicked")  // Should log in Logcat
                    Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show()

                    try {
                        val intent = Intent(this, ProfileActivity::class.java)
                        startActivity(intent)
                        Log.d("IntentCheck", "Started ProfileActivity successfully")
                    } catch (e: Exception) {
                        Log.e("IntentError", "Failed to open ProfileActivity", e)
                    }
                    true
                }
                else -> false
            }
        }

        // Handle Sidebar Navigation
        navigationView.setNavigationItemSelectedListener { menuItem ->
            drawerLayout.closeDrawer(GravityCompat.START)
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_about -> {
                    Toast.makeText(this, "About clicked", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, AboutActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_notifications -> {
                    Toast.makeText(this, "Notifications clicked", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, NotificationsActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_settings -> {
                    Toast.makeText(this, "Settings under Maintenance!", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_logout -> {
                    Toast.makeText(this, "Logout clicked", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
                R.id.nav_share -> {
                    val shareMessage = """
    Check out this awesome app: FinBuddy!
    It helps you manage your finances easily.
    Download now: https://play.google.com/store/apps/details?id=com.example.finbuddy
""".trimIndent()
                    val shareIntent = Intent(Intent.ACTION_SEND)
                    shareIntent.type = "text/plain"
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                    startActivity(Intent.createChooser(shareIntent, "Share via"))
                }
                R.id.nav_rate -> {
                    val intent = Intent(this, RateActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }
    }

    // Handle back button press to close the sidebar
    override fun onBackPressed() {
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
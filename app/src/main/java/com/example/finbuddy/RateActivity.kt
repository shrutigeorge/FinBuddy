package com.example.finbuddy

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class RateActivity<RatingBar : View?> :AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_rate)

        val ratingBar = findViewById<android.widget.RatingBar>(R.id.ratingBar)
        val ratingValue = findViewById<TextView>(R.id.ratingValue)
        val seekBar = findViewById<SeekBar>(R.id.seekBarprice)
        val seekBarValue = findViewById<TextView>(R.id.seekBarValue)
        val resultTextView = findViewById<TextView>(R.id.result)
        val submitButton = findViewById<Button>(R.id.submit)
        val spinner = findViewById<Spinner>(R.id.spinner)

        ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            ratingValue.text = rating.toString()
        }
        val spinnerItems = arrayOf("Select an Option", "General Feedback", "Bug Report", "Feature Request", "Complaint", "Suggestion")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = adapter


        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                seekBarValue.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })


        submitButton.setOnClickListener {
            val name = findViewById<EditText>(R.id.etname).text.toString()
            val rating = ratingBar.rating
            val recommendation = seekBar.progress
            val selectedSpinner = spinner.selectedItem.toString()

            resultTextView.text = """
                Name: $name
                Rating: $rating
                Recommendation: $recommendation%
                Drop-Down: $selectedSpinner
            """.trimIndent()
            resultTextView.visibility = TextView.VISIBLE
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
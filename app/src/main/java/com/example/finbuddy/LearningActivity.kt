package com.example.finbuddy

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class LearningActivity: AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_learning)

        val link1 = findViewById<TextView>(R.id.link1)
        val link2 = findViewById<TextView>(R.id.link2)
        val link3 = findViewById<TextView>(R.id.link3)
        val link4 = findViewById<TextView>(R.id.link4)
        val link5 = findViewById<TextView>(R.id.link5)
        val link6 = findViewById<TextView>(R.id.link6)
        val link7 = findViewById<TextView>(R.id.link7)
        val link8 = findViewById<TextView>(R.id.link8)
        val link9 = findViewById<TextView>(R.id.link9)
        val link10 = findViewById<TextView>(R.id.link10)


        //Intents
        link1.setOnClickListener {
            openLink("https://unacademy.com/content/upsc/study-material/commerce/income-tax/")
        }
        link2.setOnClickListener {
            openLink("https://incometaxindia.gov.in/pages/international-taxation.aspx")
        }
        link3.setOnClickListener {
            openLink("https://www.investopedia.com/taxes-4427724")
        }
        link4.setOnClickListener {
            openLink("https://www.investor.gov/introduction-investing")
        }
        link5.setOnClickListener {
            openLink("https://www.investopedia.com/articles/basics/11/3-s-simple-investing.asp")
        }
        link6.setOnClickListener {
            openLink("https://www.schwab.com/financial-planning/tools")
        }
        link7.setOnClickListener {
            openLink("https://investor.vanguard.com/investor-resources-education/article/how-to-start-investing")
        }
        link8.setOnClickListener {
            openLink("https://www.bankrate.com/banking/savings/best-high-yield-interests-savings-accounts/")
        }
        link9.setOnClickListener {
            openLink("https://economictimes.indiatimes.com/wealth/invest/this-woman-savings-scheme-offers-higher-interest-rate-than-2-year-bank-fd-and-ends-on-march-31-2025/articleshow/117139408.cms?from=mdr")
        }
        link10.setOnClickListener {
            openLink("https://www.investopedia.com/savings-4689725")
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

    private fun openLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun onBackPressed() {
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
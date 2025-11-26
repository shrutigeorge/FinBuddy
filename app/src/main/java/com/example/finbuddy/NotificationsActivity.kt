package com.example.finbuddy

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

// Define the Notification data class
data class Notification(
    val title: String,
    val message: String,
    val time: String
)

class NotificationsActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

        // Set up RecyclerView
        val notificationsRecyclerView = findViewById<RecyclerView>(R.id.notificationsRecyclerView)
        notificationsRecyclerView.layoutManager = LinearLayoutManager(this)

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

        // Sample notifications
        val notifications = listOf(
            Notification("New Message", "You have a new message from John.", "2 hours ago"),
            Notification("System Update", "A new system update is available.", "5 hours ago"),
            Notification("Reminder", "Don't forget to complete your profile.", "1 day ago")
        )

        // Set up adapter
        val adapter = NotificationAdapter(notifications)
        notificationsRecyclerView.adapter = adapter
    }
}

// Adapter for RecyclerView
class NotificationAdapter(private val notifications: List<Notification>) :
    RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_notification, parent, false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = notifications[position]
        holder.bind(notification)
    }

    override fun getItemCount(): Int = notifications.size

    class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.notificationTitle)
        private val message: TextView = itemView.findViewById(R.id.notificationMessage)
        private val time: TextView = itemView.findViewById(R.id.notificationTime)

        fun bind(notification: Notification) {
            title.text = notification.title
            message.text = notification.message
            time.text = notification.time
        }
    }
}
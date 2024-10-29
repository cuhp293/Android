package com.example.gmail

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.email.Email

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var adapter: EmailListAdapter
    private val emails = mutableListOf(
        Email("E", "Edurila.com", "Are you looking to Learn Web Design in 2022?", "12:34 PM", false),
        Email("C", "Chris Abad", "Help make Campaign Monitor better", "11:22 AM", false),
        Email("T", "Tuto.com", "8h de formation gratuite et les nouveautés Photoshop, SEO, Blender, CSS, WordPress", "11:04 AM", false),
        Email("S", "support", "Société Ovh : suivi de vos services - http://www.ovh.com 2 rue de la Motte 59100 Roubaix", "10:26 AM", false),
        Email("M", "Matt from Ionic", "The New Ionic Creator Is Here! Announcing the all-new Creator, build apps 10x faster.", "9:15 AM", false),
        Email("J", "John Doe", "This is email from John Doe", "8:30 AM", true),
        Email("S", "Sarah Smith", "Hello, this is Sarah Smith", "7:45 AM", false),
        Email("A", "Alex Johnson", "Checking in about the project", "6:00 PM", true),
        Email("K", "Kate Williams", "Hope you have a great day!", "5:30 PM", false),
        Email("D", "David Lee", "Please let me know if you need anything", "4:15 PM", true),
        Email("L", "Lisa Chen", "Attached the file you requested", "3:00 PM", false)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up Toolbar
        setSupportActionBar(findViewById(R.id.toolbar))

        // Set up ListView
        listView = findViewById(R.id.listView)
        adapter = EmailListAdapter(this, emails)
        listView.adapter = adapter

        // Handle item click
        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedEmail = emails[position]
            // Handle selected email, e.g., open a new activity or display details
            Toast.makeText(this, "Selected: ${selectedEmail.emailName}", Toast.LENGTH_SHORT).show()
        }
    }
}
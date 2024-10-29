package com.example.gmail

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.email.Email
import java.util.Random

class EmailListAdapter(
    private val context: Context,
    private val emails: List<Email>
) : ArrayAdapter<Email>(context, 0, emails) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.email_list_item, parent, false)
        }

        val email = emails[position]

        // Set initials
        val tvInitials = view?.findViewById<TextView>(R.id.tvInitials)
        tvInitials?.text = email.initials
        tvInitials?.setBackgroundColor(getRandomColor())

        // Set email name
        val tvEmailName = view?.findViewById<TextView>(R.id.tvEmailName)
        tvEmailName?.text = email.emailName

        // Set email content
        val tvEmailContent = view?.findViewById<TextView>(R.id.tvEmailContent)
        tvEmailContent?.text = email.emailContent

        // Set email time
        val tvEmailTime = view?.findViewById<TextView>(R.id.tvEmailTime)
        tvEmailTime?.text = email.emailTime

        // Set email star
        val ivEmailStar = view?.findViewById<ImageView>(R.id.ivEmailStar)
        ivEmailStar?.setImageResource(if (email.isStarred) R.drawable.ic_star else R.drawable.ic_star_border)

        return view!!
    }

    private fun getRandomColor(): Int {
        val random = Random()
        val red = random.nextInt(256)
        val green = random.nextInt(256)
        val blue = random.nextInt(256)
        return Color.rgb(red, green, blue)
    }
}
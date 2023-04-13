package com.example.cloudauth.chat

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cloudauth.R
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*

class MessagesAdapter(
    private val context: Context,
    private val messages: List<Message>,
    private val currentUserUid: String
) : RecyclerView.Adapter<MessagesAdapter.MessageViewHolder>() {

    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val messageText: TextView = itemView.findViewById(R.id.message_text)
        val timestamp: TextView = itemView.findViewById(R.id.message_time)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.message_item, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]
        holder.messageText.text = message.text
        holder.timestamp.text= message.timestamp.toString()
        val time = message.timestamp
        val sdf= SimpleDateFormat("hh:mm a",Locale.getDefault())
        val calender=Calendar.getInstance()
        calender.timeInMillis=time
        val formatedTime=sdf.format(calender.time)
        holder.timestamp.text=formatedTime
        val layoutParams = holder.messageText.layoutParams as
                LinearLayout.LayoutParams
        layoutParams.gravity = if (message.senderId == currentUserUid)
            Gravity.END else Gravity.START
        holder.messageText.layoutParams = layoutParams
        holder.timestamp.layoutParams = layoutParams
       

    }

    override fun getItemCount(): Int {
        return messages.size
    }
}

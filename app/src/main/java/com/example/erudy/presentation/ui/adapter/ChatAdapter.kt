package com.example.erudy.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.erudy.R
import com.example.erudy.data.entity.Message
import com.parse.ParseUser

class ChatAdapter(private val messages: List<Message>) :
    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    companion object {
        private const val SENT = 0
        private const val RECEIVED = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {

        val view = when (viewType) {
            SENT -> {
                LayoutInflater.from(parent.context).inflate(R.layout.sent_message_item, parent, false)
            }
            else -> {
                LayoutInflater.from(parent.context).inflate(R.layout.received_message_item, parent, false)
            }
        }
        return ChatViewHolder(view)

    }

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].writer!!.objectId == ParseUser.getCurrentUser().objectId) {
            SENT
        } else {
            RECEIVED
        }
    }

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val message: Message = messages[position]
        holder.bind(message)
    }

    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var messageText: TextView? = null

        init {
            messageText = itemView.findViewById(R.id.message_text)
        }

        fun bind(message: Message) {
            messageText?.text = message.content
        }
    }
}
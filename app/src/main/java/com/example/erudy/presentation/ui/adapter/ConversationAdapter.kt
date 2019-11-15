package com.example.erudy.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.erudy.R
import com.example.erudy.data.entity.Conversation
import com.example.erudy.data.entity.Request
import com.parse.ParseUser

class ConversationAdapter (private val convs: List<Conversation>, private val listener: ClickOnRecycler) :
        RecyclerView.Adapter<ConversationAdapter.ConvsViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConvsViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return ConvsViewHolder(inflater, parent, listener)
        }

        override fun getItemCount(): Int = convs.size

        override fun onBindViewHolder(holder: ConvsViewHolder, position: Int) {
            val conv: Conversation = convs[position]
            holder.bind(conv)
        }

        class ConvsViewHolder(inflater: LayoutInflater,
                                 parent: ViewGroup,
                                 private val listener: ClickOnRecycler
        ) : RecyclerView.ViewHolder(inflater.inflate( R.layout.conversation_item, parent, false)) {

            private var title: TextView? = null
            private var user: TextView? = null

            init {
                title = itemView.findViewById(R.id.conv_title)
                user = itemView.findViewById(R.id.conv_other_user)
            }

            fun bind(conv: Conversation) {
                itemView.setOnClickListener {
                    listener.convClicked(conv)
                }

                title?.text = conv.request!!.title

                if(conv.user1!!.objectId != ParseUser.getCurrentUser().objectId) {
                    user?.text = conv.user1!!.fullName
                } else {
                    user?.text = conv.user2!!.fullName
                }
            }
        }

        interface ClickOnRecycler {
            fun convClicked(conv: Conversation)
        }
    }
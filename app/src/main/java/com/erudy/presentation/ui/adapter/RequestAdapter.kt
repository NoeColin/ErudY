package com.erudy.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.erudy.R
import com.erudy.data.entity.Request


class RequestAdapter(private val requests: List<Request>, private val listener: ClickOnRecycler) :
    RecyclerView.Adapter<RequestAdapter.RequestsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RequestsViewHolder(inflater, parent, listener)
    }

    override fun getItemCount(): Int = requests.size

    override fun onBindViewHolder(holder: RequestsViewHolder, position: Int) {
        val request: Request = requests[position]
        holder.bind(request)
    }

    class RequestsViewHolder(inflater: LayoutInflater,
                             parent: ViewGroup,
                             private val listener: ClickOnRecycler
    ) : RecyclerView.ViewHolder(inflater.inflate( R.layout.request_item, parent, false)) {

        private var title: TextView? = null
        private var content: TextView? = null
        private var creator: TextView? = null

        init {
            title = itemView.findViewById(R.id.request_title)
            content = itemView.findViewById(R.id.request_content)
            creator = itemView.findViewById(R.id.request_creator)
        }

        fun bind(request: Request) {
            itemView.setOnClickListener {
                listener.requestClicked(request.objectId)
            }

            title?.text = request.title
            content?.text = request.description
            creator?.text = request.owner!!.fullName
        }
    }

    interface ClickOnRecycler {
        fun requestClicked(idRequest: String)
    }
}
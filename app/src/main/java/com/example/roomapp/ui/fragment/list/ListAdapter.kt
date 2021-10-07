package com.example.roomapp.ui.fragment.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.R
import com.example.roomapp.data.model.User

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val mId: TextView = itemView.findViewById<View>(R.id.item_id) as TextView
        private val mName: TextView = itemView.findViewById<View>(R.id.item_name) as TextView
        private val mSurname: TextView = itemView.findViewById<View>(R.id.item_surname) as TextView
        private val mAge: TextView = itemView.findViewById<View>(R.id.item_age) as TextView

        fun bind(user: User){
            mId.text = user.id.toString()
            mName.text = user.firstName
            mSurname.text = user.lastName
            mAge.text = user.age.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.bind(currentItem)
        holder.itemView.findViewById<View>(R.id.item_layout).setOnClickListener {
            val actions = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(actions)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(userList: List<User>) {
        this.userList = userList
        notifyDataSetChanged()
    }

}
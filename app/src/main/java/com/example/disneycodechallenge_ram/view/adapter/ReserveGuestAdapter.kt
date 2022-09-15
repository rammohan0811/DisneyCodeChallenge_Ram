package com.example.disneycodechallenge_ram.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.disneycodechallenge_ram.R
import com.example.disneycodechallenge_ram.db.ReservedGuest
import kotlinx.android.synthetic.main.item_select_guest.view.*

class ReserveGuestAdapter(private val onItemClicked: (ReservedGuest) -> Unit) : RecyclerView.Adapter<ReserveGuestAdapter.GuestViewHolder>(){

    private var data : ArrayList<ReservedGuest>?=null

    fun setData(list: ArrayList<ReservedGuest>){
        data = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        return GuestViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_select_guest, parent, false))
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        val item = data?.get(position)
        holder.bindView(item, position)

        if (item!!.isSelected) {
            holder.itemView.cb_guest_name.setChecked(true);
        } else {
            holder.itemView.cb_guest_name.setChecked(false);
        }

        var selectedPos = holder.itemView.cb_guest_name.tag

        holder.itemView.cb_guest_name.setOnClickListener {
            if (data != null) {
                for (k in 0..(itemCount-1)) {
                    if (k == selectedPos) {
                        data!![selectedPos].isSelected  = true
                    } else {
                        data!![k].isSelected = false
                    }
                }
            }
            this.notifyDataSetChanged()
            onItemClicked(item)
        }
    }

    class GuestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindView(item: ReservedGuest?, position: Int) {
            if (item != null) {
                itemView.cb_guest_name.text = item.guestName
                itemView.cb_guest_name.tag = position
            }
        }
    }
}
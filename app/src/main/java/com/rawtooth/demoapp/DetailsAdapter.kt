package com.rawtooth.demoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DetailsAdapter :RecyclerView.Adapter<DetailsAdapter.DetailVewHolder>(){
private var dtsList:ArrayList<DetailModel> = ArrayList()
    fun addItem(item:ArrayList<DetailModel>){
        this.dtsList=item
        notifyDataSetChanged()
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DetailVewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card,parent,false)
    )
    override fun onBindViewHolder(holder: DetailVewHolder, position: Int) {
        val dts=dtsList[position]
        holder.bindView(dts)
    }

    override fun getItemCount(): Int {
        return dtsList.size
    }
    class DetailVewHolder(var view:View) :RecyclerView.ViewHolder(view){
        private var id = view.findViewById<TextView>(R.id.card_tv1)
        private var name=view.findViewById<TextView>(R.id.card_tv2)
        private var email=view.findViewById<TextView>(R.id.card_tv3)
        fun bindView(dts:DetailModel){
            id.text=dts.id.toString()
            name.text=dts.name
            email.text=dts.email
        }

    }
}
package com.example.qbuser.countrylist.adapators

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.qbuser.countrylist.R
import com.example.qbuser.countrylist.models.CountryResponse
import kotlinx.android.synthetic.main.country_list.view.*

class CountryAdaptor(val countryList: List<CountryResponse.Country>): RecyclerView.Adapter<CountryAdaptor.ViewHolder>(){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.name?.text = countryList[position].name
        holder.isd_code?.text = countryList[position].isd_code
        holder.code?.text = countryList[position].code
        Glide.with(holder.itemView).load(countryList[position].icon).into(holder.image)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.country_list,parent, false)
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name = itemView.name
        var code = itemView.code
        var isd_code = itemView.isd_code
        var image = itemView.imageView_id

    }
}
package com.example.android.contentproviderexample

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.android.contentproviderexample.model.Contact

class ContactAdapter(var dsDanhBa: ArrayList<Contact>, val context: Context, val layoutInflater: LayoutInflater) :
    BaseAdapter() {

    fun setData(dsDanhBa: ArrayList<Contact>) {
        this.dsDanhBa.clear()
        this.dsDanhBa.addAll(dsDanhBa)
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        view = layoutInflater.inflate(R.layout.item, null)
        val txtName = view!!.findViewById(R.id.txtName) as TextView
        val txtPhone = view!!.findViewById(R.id.txtPhone) as TextView
        txtName.text = dsDanhBa[position].builder.name
        txtPhone.text = dsDanhBa[position].builder.phone
        return view
    }

    override fun getItem(position: Int): Any {
        return dsDanhBa[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dsDanhBa.size
    }

}

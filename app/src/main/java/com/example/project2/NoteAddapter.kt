package com.example.project2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.note_latout.view.*

class NoteAddapter(context: Context, noteList: ArrayList<Nots>) : ArrayAdapter<Nots>(context,0,noteList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
       val view =  LayoutInflater.from(context).inflate(R.layout.note_latout, parent,false )
        val nots: Nots= this!!.getItem(position)!!

        view.textViewName.text =nots.name
        view.textViewNumber.text =nots.number
        view.textViewAddress.text =nots.address
        return view




    }
}
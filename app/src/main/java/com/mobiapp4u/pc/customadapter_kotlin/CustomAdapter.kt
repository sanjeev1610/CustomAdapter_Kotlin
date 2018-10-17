package com.mobiapp4u.pc.customadapter_kotlin

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.row_item_of_mainactivity.view.*
import java.io.File

class CustomAdapter(var context:MainActivity, var list:Array<File>) : BaseAdapter(){


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.row_item_of_mainactivity,parent,false)
        var file = list[position]
        view.imgView_item.setImageURI(Uri.fromFile(file))
        view.textView_fileName_item.text = file.name
        //view.textView_filesize.text = file.length().toString()
        view.button_delete_item.setOnClickListener {
            file.delete()
            context.readFiles()
        }
        return view
    }

    override fun getItem(position: Int): Any {
        return 0
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return list.size
    }

}
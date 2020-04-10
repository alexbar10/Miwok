package com.alexbar10.miwok

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat

class WordAdapter(context: Context, @LayoutRes itemResourceId: Int, values: List<Word>, val colorResourceId: Int? = null) :
    ArrayAdapter<Word>(context, itemResourceId, values) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Check if the exiting view is being reused
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }

        // get the Word for the position
        val word = getItem(position)

        // get the custom views inside item_view
        val title = itemView?.findViewById<TextView>(R.id.title_item)
        title?.text = word?.miwokVersion

        val subtitle = itemView?.findViewById<TextView>(R.id.subtitle_item)
        subtitle?.text = word?.englishVersion

        val image = itemView?.findViewById<ImageView>(R.id.image_view)
        word?.imageResourceId?.let {
            image?.setImageResource(it)
            image?.visibility = View.VISIBLE
        }

        val linearLayoutText = itemView?.findViewById<LinearLayout>(R.id.linear_layout_texts)
        colorResourceId?.let {
            linearLayoutText?.setBackgroundColor(ContextCompat.getColor(context, it))
        }

        return itemView!!
    }
}
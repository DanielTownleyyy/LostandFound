package com.DanielTownley.lostandfound

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ItemListAdapter(
    private val context: Context,
    private var items: List<Item>
) : BaseAdapter() {

    // Updates the adapter data and refreshes the ListView
    fun updateItems(newItems: List<Item>) {
        this.items = newItems
        notifyDataSetChanged()
    }

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Any = items[position]

    override fun getItemId(position: Int): Long = items[position].id.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.item_list_row, parent, false
        )

        val item = items[position]

        // Assuming your item_list_row.xml has these TextViews
        val nameTextView = view.findViewById<TextView>(R.id.item_name)
        val typeTextView = view.findViewById<TextView>(R.id.item_type)
        val dateTextView = view.findViewById<TextView>(R.id.item_date)
        val locationTextView = view.findViewById<TextView>(R.id.item_location)

        nameTextView.text = item.name
        typeTextView.text = item.type
        dateTextView.text = item.date
        locationTextView.text = item.location

        return view
    }
}

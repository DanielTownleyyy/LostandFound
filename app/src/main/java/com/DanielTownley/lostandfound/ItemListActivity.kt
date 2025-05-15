package com.DanielTownley.lostandfound

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ItemListActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var adapter: ItemListAdapter  // Assuming you have a custom adapter class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        listView = findViewById(R.id.item_list_view)
        dbHelper = DatabaseHelper(this)

        val items = dbHelper.getAllItemsList() // Fetch the List<Item> from DB

        adapter = ItemListAdapter(this, items)
        listView.adapter = adapter

        // When an item is clicked, open the remove screen (or detail screen)
        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedItem = items[position]
            val intent = Intent(this, ItemRemoveActivity::class.java)
            intent.putExtra("item_id", selectedItem.id)  // Assuming your Item class has an id property
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh the list when coming back to this activity
        val items = dbHelper.getAllItemsList()
        adapter.updateItems(items) // Make sure your adapter supports updating the list
    }
}

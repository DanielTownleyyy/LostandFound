package com.DanielTownley.lostandfound

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ItemRemoveActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remove_item)

        val removeButton: Button = findViewById(R.id.remove_button)
        val itemId = intent.getIntExtra("item_id", -1)

        val dbHelper = DatabaseHelper(this)

        removeButton.setOnClickListener {
            if (itemId != -1) {
                dbHelper.deleteItem(itemId)
                Toast.makeText(this, "Item removed successfully!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}

package com.DanielTownley.lostandfound

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCreateAdvert = findViewById<Button>(R.id.btnCreateAdvert)
        val btnViewItems = findViewById<Button>(R.id.btnViewItems)
        val btnRemoveAdvert = findViewById<Button>(R.id.btnRemoveAdvert)

        btnCreateAdvert.setOnClickListener {
            val intent = Intent(this, CreateAdvertActivity::class.java)
            startActivity(intent)
        }

        btnViewItems.setOnClickListener {
            val intent = Intent(this, ItemListActivity::class.java)
            startActivity(intent)
        }

        btnRemoveAdvert.setOnClickListener {
            val intent = Intent(this, ItemListActivity::class.java)
            startActivity(intent)
        }
    }
}

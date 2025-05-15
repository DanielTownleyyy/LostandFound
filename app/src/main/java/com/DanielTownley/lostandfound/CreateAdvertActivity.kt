package com.DanielTownley.lostandfound

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class CreateAdvertActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_advert)

        val saveButton = findViewById<Button>(R.id.btnSaveAdvert)
        val nameEditText = findViewById<EditText>(R.id.etItemName)
        val phoneEditText = findViewById<EditText>(R.id.etPhone)
        val descriptionEditText = findViewById<EditText>(R.id.etDescription)
        val dateEditText = findViewById<EditText>(R.id.etDate)
        val locationEditText = findViewById<EditText>(R.id.etLocation)
        val postTypeSpinner = findViewById<Spinner>(R.id.spinnerPostType)

        val dbHelper = DatabaseHelper(this)

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val date = dateEditText.text.toString()
            val location = locationEditText.text.toString()
            val type = postTypeSpinner.selectedItem.toString() // "Lost" or "Found"

            dbHelper.insertItem(name, type, description, phone, date, location)
            Toast.makeText(this, "Item saved successfully!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}

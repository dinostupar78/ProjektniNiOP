package com.example.projektniniop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AddActivity : AppCompatActivity() {
    private lateinit var addbtn: Button
    private lateinit var name_input: EditText
    private lateinit var steps_input: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        addbtn=findViewById(R.id.addbtn)
        name_input=findViewById(R.id.name_input)
        steps_input=findViewById(R.id.steps_input)

        addbtn.setOnClickListener {
            val name = name_input.text.toString()
            val steps = steps_input.text.toString().toInt()

            val db = MyDatabaseHelper(this)
            db.addGnom(name.trim(), steps)
        }
    }
}
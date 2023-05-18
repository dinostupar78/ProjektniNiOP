package com.example.projektniniop

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projektniniop.AddActivity
import com.example.projektniniop.MyDatabaseHelper
import com.example.projektniniop.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class DatabaseActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var addbtn: FloatingActionButton
    lateinit var db: MyDatabaseHelper
    private var gnom_id: ArrayList<String> = ArrayList()
    private var gnom_name: ArrayList<String> = ArrayList()
    private var gnom_price: ArrayList<String> = ArrayList()
    private lateinit var customAdapter: CustomAdapter



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigationView.selectedItemId = R.id.bottom_database
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_home -> {
                    startActivity(Intent(applicationContext, HomeActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                    true
                }
                R.id.bottom_settings -> true
                else -> false
            }
        }
        recyclerView = findViewById(R.id.recyclerView)
        addbtn = findViewById(R.id.addbtn)

        addbtn.setOnClickListener{
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

        db = MyDatabaseHelper(this@DatabaseActivity)

        storeDataInArrays()

        customAdapter = CustomAdapter(this@DatabaseActivity, gnom_id, gnom_name, gnom_price)
        recyclerView.adapter = customAdapter
        recyclerView.layoutManager = LinearLayoutManager(this@DatabaseActivity)

    }

    private fun storeDataInArrays() {
        val cursor = db.readAllData()
        if (cursor != null) {
            if (cursor.count == 0) {
                Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show()
            } else {
                while (cursor.moveToNext()) {
                    gnom_id.add(cursor.getString(0))
                    gnom_name.add(cursor.getString(1))
                    gnom_price.add(cursor.getString(2))
                }

            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_all) {
            confirmDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    fun confirmDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete All?")
        builder.setMessage("Are you sure you want to delete all Data?")
        builder.setPositiveButton("Yes") { dialogInterface, i ->
            val myDB = MyDatabaseHelper(this)
            myDB.deleteAllData()
            //Refresh Activity
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            finish()
        }
        builder.setNegativeButton("No") { dialogInterface, i -> }
        builder.create().show()
    }

}
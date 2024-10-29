package com.example.androidex2

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val etNumber = findViewById<EditText>(R.id.etNumber)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val btnShow = findViewById<Button>(R.id.btnShow)
        val listView = findViewById<ListView>(R.id.listView)
        val tvError = findViewById<TextView>(R.id.tvError)
        btnShow.setOnClickListener {
            tvError.visibility = TextView.GONE

            val input = etNumber.text.toString()
            val n = input.toIntOrNull()

            if (n == null || n <= 0) {
                tvError.text = "Vui lòng nhập số nguyên dương hợp lệ"
                tvError.visibility = TextView.VISIBLE
                return@setOnClickListener
            }

            val selectedRadioButtonId = radioGroup.checkedRadioButtonId
            val result = mutableListOf<Int>()

            when (selectedRadioButtonId) {
                R.id.rbEven -> {
                    for (i in 0..n step 2) result.add(i)
                }
                R.id.rbOdd -> {
                    for (i in 1..n step 2) result.add(i)
                }
                R.id.rbPerfectSquare -> {
                    var i = 0
                    while (i * i <= n) {
                        result.add(i * i)
                        i++
                    }
                }
                else -> {
                    tvError.text = "Vui lòng chọn loại số"
                    tvError.visibility = TextView.VISIBLE
                    return@setOnClickListener
                }
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, result)
            listView.adapter = adapter
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
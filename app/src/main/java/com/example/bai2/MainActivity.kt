package com.example.bai2

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var edtNumber: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var btnShow: Button
    private lateinit var listView: ListView
    private lateinit var tvError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtNumber = findViewById(R.id.edtNumber)
        radioGroup = findViewById(R.id.radioGroup)
        btnShow = findViewById(R.id.btnShow)
        listView = findViewById(R.id.listView)
        tvError = findViewById(R.id.tvError)

        btnShow.setOnClickListener {
            val number = edtNumber.text.toString().toIntOrNull()

            if (number == null || number < 0) {
                tvError.text = "Vui lòng nhập số nguyên dương hợp lệ."
                tvError.visibility = View.VISIBLE
                listView.adapter = null
                return@setOnClickListener
            } else {
                tvError.visibility = View.GONE
            }

            val selectedId = radioGroup.checkedRadioButtonId
            val result = mutableListOf<Int>()

            when (selectedId) {
                R.id.radioEven -> {
                    for (i in 0..number step 2) {
                        result.add(i)
                    }
                }
                R.id.radioOdd -> {
                    for (i in 1..number step 2) {
                        result.add(i)
                    }
                }
                R.id.radioPerfectSquare -> {
                    for (i in 0..number) {
                        val sqrt = Math.sqrt(i.toDouble()).toInt()
                        if (sqrt * sqrt == i) {
                            result.add(i)
                        }
                    }
                }
                else -> {
                    tvError.text = "Vui lòng chọn loại số."
                    tvError.visibility = View.VISIBLE
                    listView.adapter = null
                    return@setOnClickListener
                }
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, result)
            listView.adapter = adapter
        }
    }
}

package com.alexbar10.miwok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        view_pager.adapter = MiwokFragmentAdapter(supportFragmentManager)
/*
        numbers.setOnClickListener {
            startActivity(Intent(this, NumbersActivity::class.java))
        }
        family.setOnClickListener {
            startActivity(Intent(this, FamilyActivity::class.java))
        }
        colors.setOnClickListener {
            startActivity(Intent(this, ColorsActivity::class.java))
        }
        phrases.setOnClickListener {
            startActivity(Intent(this, PhrasesActivity::class.java))
        }
        */
    }
}

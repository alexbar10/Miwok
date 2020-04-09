package com.alexbar10.miwok

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_colors.*

class ColorsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_colors)

        val numbers: MutableList<Word> = arrayListOf(
            Word("red", "weṭeṭṭi"),
            Word("green", "chokokki"),
            Word("brown", "ṭakaakki"),
            Word("gray", "ṭopoppi"),
            Word("black", "kululli"),
            Word("white", "kelelli"),
            Word("dusty yellow", "ṭopiisә"),
            Word("mustard yellow", "chiwiiṭә")
        )

        val adapter = WordAdapter(this, R.layout.list_item, numbers)
        list_view_colors.adapter = adapter
    }
}

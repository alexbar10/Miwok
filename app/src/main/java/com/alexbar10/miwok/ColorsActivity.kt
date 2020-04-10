package com.alexbar10.miwok

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.word_list.*

class ColorsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.word_list)

        val numbers: MutableList<Word> = arrayListOf(
            Word("red", "weṭeṭṭi", R.drawable.color_red),
            Word("green", "chokokki", R.drawable.color_green),
            Word("brown", "ṭakaakki", R.drawable.color_brown),
            Word("gray", "ṭopoppi", R.drawable.color_gray),
            Word("black", "kululli", R.drawable.color_black),
            Word("white", "kelelli", R.drawable.color_white),
            Word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow),
            Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow)
        )

        val adapter = WordAdapter(this, R.layout.list_item, numbers, R.color.category_colors)
        list_view.adapter = adapter
    }
}

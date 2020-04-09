package com.alexbar10.miwok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_phrases.*

class PhrasesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phrases)

        val phrases: MutableList<Word> = arrayListOf(
            Word("Where are you going?", "minto wuksus\n"),
            Word("What is your name?", "tinnә oyaase'nә"),
            Word("My name is..", "oyaaset..."),
            Word("How are you feeling?", "michәksәs?"),
            Word("I’m feeling good.", "kuchi achit"),
            Word("Are you coming?", "әәnәs'aa?"),
            Word("Yes, I’m coming.", "hәә’ әәnәm"),
            Word("Let’s go.", "әәnәm")
        )

        val adapter = WordAdapter(this, R.layout.list_item, phrases)
        list_view_phrases.adapter = adapter
    }
}

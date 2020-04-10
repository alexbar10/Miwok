package com.alexbar10.miwok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.word_list.*

class PhrasesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.word_list)

        val phrases: MutableList<Word> = arrayListOf(
            Word("Where are you going?", "minto wuksus"),
            Word("What is your name?", "tinnә oyaase'nә"),
            Word("My name is..", "oyaaset..."),
            Word("How are you feeling?", "michәksәs?"),
            Word("I’m feeling good.", "kuchi achit"),
            Word("Are you coming?", "әәnәs'aa?"),
            Word("Yes, I’m coming.", "hәә’ әәnәm"),
            Word("Let’s go.", "әәnәm")
        )

        val adapter = WordAdapter(this, R.layout.list_item, phrases, R.color.category_phrases)
        list_view.adapter = adapter
    }
}

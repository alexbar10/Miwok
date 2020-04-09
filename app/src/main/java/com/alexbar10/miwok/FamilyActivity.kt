package com.alexbar10.miwok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.word_list.*

class FamilyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.word_list)

        val familyWords: MutableList<Word> = arrayListOf(
            Word("father", "әpә"),
            Word("mother", "әṭa"),
            Word("son", "angsi"),
            Word("daughter", "tune"),
            Word("older brother", "taachi"),
            Word("younger brother", "chalitti"),
            Word("older sister", "teṭe"),
            Word("younger sister", "kolliti")
        )

        val adapter = WordAdapter(this, R.layout.list_item, familyWords)
        list_view.adapter = adapter
    }
}

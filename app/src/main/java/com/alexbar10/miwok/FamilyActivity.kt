package com.alexbar10.miwok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.word_list.*

class FamilyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.word_list)

        val familyWords: MutableList<Word> = arrayListOf(
            Word("father", "әpә", R.drawable.family_father),
            Word("mother", "әṭa", R.drawable.family_mother),
            Word("son", "angsi", R.drawable.family_son),
            Word("daughter", "tune", R.drawable.family_daughter),
            Word("older brother", "taachi", R.drawable.family_older_brother),
            Word("younger brother", "chalitti", R.drawable.family_younger_brother),
            Word("older sister", "teṭe", R.drawable.family_older_sister),
            Word("younger sister", "kolliti", R.drawable.family_younger_sister)
        )

        val adapter = WordAdapter(this, R.layout.list_item, familyWords, R.color.category_family)
        list_view.adapter = adapter
    }
}

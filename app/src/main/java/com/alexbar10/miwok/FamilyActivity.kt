package com.alexbar10.miwok

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.word_list.*

class FamilyActivity : AppCompatActivity() {

    var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.word_list)

        val familyWords: MutableList<Word> = arrayListOf(
            Word("father", "әpә", R.drawable.family_father, R.raw.family_father),
            Word("mother", "әṭa", R.drawable.family_mother, R.raw.family_mother),
            Word("son", "angsi", R.drawable.family_son, R.raw.family_son),
            Word("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter),
            Word("older brother", "taachi", R.drawable.family_older_brother, R.raw.family_older_brother),
            Word("younger brother", "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother),
            Word("older sister", "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister),
            Word("younger sister", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister)
        )

        val adapter = WordAdapter(this, R.layout.list_item, familyWords, R.color.category_family)
        list_view.adapter = adapter

        list_view.setOnItemClickListener { parent, view, position, id ->
            // Get the item selected
            val wordSelected = parent.getItemAtPosition(position) as? Word

            // Create media player
            wordSelected?.soundResourceId?.let {
                mediaPlayer = MediaPlayer.create(this, it)
                mediaPlayer?.start()
            }
        }
    }
}

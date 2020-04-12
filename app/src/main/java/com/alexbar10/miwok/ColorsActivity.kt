package com.alexbar10.miwok

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.word_list.*

class ColorsActivity : AppCompatActivity() {

    var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.word_list)

        val numbers: MutableList<Word> = arrayListOf(
            Word("red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red),
            Word("green", "chokokki", R.drawable.color_green, R.raw.color_green),
            Word("brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown),
            Word("gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray),
            Word("black", "kululli", R.drawable.color_black, R.raw.color_black),
            Word("white", "kelelli", R.drawable.color_white, R.raw.color_white),
            Word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow),
            Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow)
        )

        val adapter = WordAdapter(this, R.layout.list_item, numbers, R.color.category_colors)
        list_view.adapter = adapter

        list_view.setOnItemClickListener { parent, view, position, id ->
            // Delete other instance of media player if there's one
            releaseHelper()

            // Get the item selected
            val wordSelected = parent.getItemAtPosition(position) as? Word

            // Create media player
            wordSelected?.soundResourceId?.let {
                mediaPlayer = MediaPlayer.create(this, it)
                mediaPlayer?.start()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        releaseHelper()
    }

    private fun releaseHelper() {
        if (mediaPlayer != null) {
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }
}

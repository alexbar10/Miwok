package com.alexbar10.miwok

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.word_list.*

class PhrasesActivity : AppCompatActivity() {

    var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.word_list)

        val phrases: MutableList<Word> = arrayListOf(
            Word("Where are you going?", "minto wuksus", null, R.raw.phrase_where_are_you_going),
            Word("What is your name?", "tinnә oyaase'nә", null, R.raw.phrase_what_is_your_name),
            Word("My name is..", "oyaaset...", null, R.raw.phrase_my_name_is),
            Word("How are you feeling?", "michәksәs?", null, R.raw.phrase_how_are_you_feeling),
            Word("I’m feeling good.", "kuchi achit", null, R.raw.phrase_im_feeling_good),
            Word("Are you coming?", "әәnәs'aa?", null, R.raw.phrase_are_you_coming),
            Word("Yes, I’m coming.", "hәә’ әәnәm", null, R.raw.phrase_yes_im_coming),
            Word("Let’s go.", "әәnәm", null, R.raw.phrase_lets_go)
        )

        val adapter = WordAdapter(this, R.layout.list_item, phrases, R.color.category_phrases)
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

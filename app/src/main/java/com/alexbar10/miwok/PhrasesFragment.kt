package com.alexbar10.miwok

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.word_list.view.*

/**
 * A simple [Fragment] subclass.
 */
class PhrasesFragment : Fragment(), AudioManager.OnAudioFocusChangeListener {

    var mediaPlayer: MediaPlayer? = null
    var audioManager: AudioManager? = null
    var focusRequest: AudioFocusRequest? = null
    var playbackAttributes: AudioAttributes? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.word_list, container, false)

        audioManager = activity!!.getSystemService(Context.AUDIO_SERVICE) as AudioManager?

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

        val adapter = WordAdapter(activity!!, R.layout.list_item, phrases, R.color.category_phrases)
        rootView.list_view.adapter = adapter

        rootView.list_view.setOnItemClickListener { parent, _, position, _ ->
            // Delete other instance of media player if there's one
            releaseHelper()

            // Get the item selected
            val wordSelected = parent.getItemAtPosition(position) as? Word

            // Request focus
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                setupManager(wordSelected)
            }
        }

        return rootView
    }

    override fun onStop() {
        super.onStop()
        releaseHelper()
    }

    /**
     * Helper methods
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setupManager(wordSelected: Word?) {
        playbackAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            focusRequest = AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
                .setAudioAttributes(playbackAttributes ?: return)
                .setWillPauseWhenDucked(false)
                .setOnAudioFocusChangeListener(this)
                .build()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            when (focusRequest?.let { audioManager?.requestAudioFocus(it) }) {
                AudioManager.AUDIOFOCUS_REQUEST_GRANTED -> {
                    Log.d("Word Selected", wordSelected.toString())

                    // Create media player
                    wordSelected?.soundResourceId?.let {
                        mediaPlayer = MediaPlayer.create(activity, it)
                        mediaPlayer?.start()
                        mediaPlayer?.setOnCompletionListener {
                            releaseHelper()
                        }
                    }
                }
                AudioManager.AUDIOFOCUS_REQUEST_FAILED -> {
                    Log.d("AudioManager", "AUDIOFOCUS_REQUEST_FAILED")
                    mediaPlayer?.stop()
                }
            }
        }
    }

    private fun releaseHelper() {
        if (mediaPlayer != null) {
            mediaPlayer?.release()
            mediaPlayer = null

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                focusRequest?.let { audioManager?.abandonAudioFocusRequest(it) }
            }
        }
    }

    /**
     * Implement OnAudioFocusChangeListener
     */
    override fun onAudioFocusChange(focusChange: Int) {
        when (focusChange) {
            AudioManager.AUDIOFOCUS_GAIN -> {
                Log.d("onAudioFocusChange", "AUDIOFOCUS_GAIN")
                mediaPlayer?.start()
            }
            AudioManager.AUDIOFOCUS_LOSS -> {
                Log.d("onAudioFocusChange", "AUDIOFOCUS_LOSS")
                releaseHelper()
            }
            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK, AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> {
                mediaPlayer?.pause()
                mediaPlayer?.seekTo(0)
                Log.d("onAudioFocusChange", "AUDIOFOCUS_LOSS_TRANSIENT/_CAN_DUCK")
            }
        }
    }
}
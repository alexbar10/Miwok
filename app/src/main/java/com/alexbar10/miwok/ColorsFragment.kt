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
class ColorsFragment : Fragment(), AudioManager.OnAudioFocusChangeListener {

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

        val adapter = WordAdapter(activity!!, R.layout.list_item, numbers, R.color.category_colors)
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
                focusRequest?.let {  audioManager?.abandonAudioFocusRequest(it) }
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

package com.alexbar10.miwok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.word_list.*

class NumbersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.word_list)

        // Array of numbers
        val numbers: MutableList<Word> = arrayListOf(
            Word("one", "lutti"),
            Word("two", "otiiko"),
            Word("three", "tolookosu"),
            Word("four", "oyyisa"),
            Word("five", "massokka"),
            Word("six", "temmokka"),
            Word("seven", "kenekaku"),
            Word("eight", "kawinta"),
            Word("nine", "wo’e"),
            Word("ten", "na’aacha")
        )

        val adapter = WordAdapter(this, R.layout.list_item, numbers)
        list_view.adapter = adapter

        // Create views and add it to the root view
  /*      var index = 0
        while (index < numbers.size) {
            val textView = TextView(this)
            textView.text = numbers[index]
            linear_layout.addView(textView)

            index++
        }
*/
        /*
        for (number in numbers) {
            val textView = TextView(this)
            textView.text = number
            linear_layout.addView(textView)
        }
        */
    }
}

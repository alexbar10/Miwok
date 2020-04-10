package com.alexbar10.miwok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.list_item.*
import kotlinx.android.synthetic.main.word_list.*

class NumbersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.word_list)

        // Array of numbers
        val numbers: MutableList<Word> = arrayListOf(
            Word("one", "lutti", R.drawable.number_one),
            Word("two", "otiiko", R.drawable.number_two),
            Word("three", "tolookosu", R.drawable.number_three),
            Word("four", "oyyisa", R.drawable.number_four),
            Word("five", "massokka", R.drawable.number_five),
            Word("six", "temmokka", R.drawable.number_six),
            Word("seven", "kenekaku", R.drawable.number_seven),
            Word("eight", "kawinta", R.drawable.number_eight),
            Word("nine", "wo’e", R.drawable.number_nine),
            Word("ten", "na’aacha", R.drawable.number_ten)
        )

        val adapter = WordAdapter(this, R.layout.list_item, numbers, R.color.category_numbers)
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

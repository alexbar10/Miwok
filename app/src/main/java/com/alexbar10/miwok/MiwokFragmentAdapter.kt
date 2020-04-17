package com.alexbar10.miwok

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MiwokFragmentAdapter(private val context: Context, fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return NumbersFragment()
            1 -> return FamilyFragment()
            2 -> return ColorsFragment()
            3 -> return PhrasesFragment()
        }
        return Fragment()
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context.getString(R.string.category_numbers)
            1 -> context.getString(R.string.category_family)
            2 -> context.getString(R.string.category_colors)
            3 -> context.getString(R.string.category_phrases)
            else -> context.getString(R.string.category_numbers)
        }
    }
}
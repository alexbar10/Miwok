package com.alexbar10.miwok

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MiwokFragmentAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
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
}
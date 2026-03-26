package com.example.marchwhatsapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.marchwhatsapp.ui.CallsFragment
import com.example.marchwhatsapp.ui.ChatsFragment
import com.example.marchwhatsapp.ui.StatusFragment

class FragmentViewPagerAdapter(supportFragmentManager: FragmentManager) :
    FragmentPagerAdapter(supportFragmentManager) {
    override fun getItem(p0: Int): Fragment {
        return when (p0) {
            0 -> ChatsFragment()
            1 -> CallsFragment()
            2 -> StatusFragment()
            else -> ChatsFragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Chats"
            1 -> return "Calls"
            2 -> return "Status"
        }
        return super.getPageTitle(position)
    }
}
package tech.gamedev.codeninjas.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnBoardingAdapter(val list: ArrayList<Fragment>,fm: Fragment): FragmentStateAdapter(fm) {

    override fun getItemCount(): Int =list.size


    override fun createFragment(position: Int): Fragment = list[position]

}
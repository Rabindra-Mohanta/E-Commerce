package alkusi.mahato.e_commerce.screens.Tab.Adapters

import alkusi.mahato.e_commerce.screens.Cart.fragments.CartFragment
import alkusi.mahato.e_commerce.screens.Favorite.fragments.FavoriteFragment
import alkusi.mahato.e_commerce.screens.Home.Fragments.HomeFragment
import alkusi.mahato.e_commerce.screens.Profile.fragments.ProfileFragment
import alkusi.mahato.e_commerce.screens.ordered.fragments.OrderedFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabFragmentStateAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> OrderedFragment()
            2 -> CartFragment()
            3 -> FavoriteFragment()
            else ->
                HomeFragment()

        }
    }
}
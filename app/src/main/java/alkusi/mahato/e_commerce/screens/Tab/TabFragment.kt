package alkusi.mahato.e_commerce.screens.Tab

import alkusi.mahato.e_commerce.R
import alkusi.mahato.e_commerce.databinding.FragmentTabBinding
import alkusi.mahato.e_commerce.library.views.BaseFragment
import alkusi.mahato.e_commerce.screens.Tab.Adapters.TabFragmentStateAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout


class TabFragment : BaseFragment<FragmentTabBinding>(R.layout.fragment_tab) {

    override fun FragmentTabBinding.initialize() {
        setToolbar()
        init()
    }
    private fun setToolbar() {
        val activity = (requireActivity() as AppCompatActivity)
        activity.setSupportActionBar(binding.newToolbar.toolbar)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setHomeAsUpIndicator(R.drawable.menu_icon)
        activity.title = resources.getString(R.string.app_name)
    }
    private fun init() {
        //set adapter
        binding.viewPager2.adapter = TabFragmentStateAdapter(childFragmentManager, lifecycle)
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewPager2.currentItem = tab?.position ?: 0
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })
    }

}
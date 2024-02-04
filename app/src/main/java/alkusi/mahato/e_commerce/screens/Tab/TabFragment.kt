package alkusi.mahato.e_commerce.screens.Tab

import alkusi.mahato.e_commerce.R
import alkusi.mahato.e_commerce.databinding.FragmentTabBinding
import alkusi.mahato.e_commerce.datahelper.SharedPrefHelper
import alkusi.mahato.e_commerce.library.views.BaseFragment
import alkusi.mahato.e_commerce.screens.Tab.Adapters.TabFragmentStateAdapter
import android.app.Dialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.saadahmedsoft.popupdialog.PopupDialog
import com.saadahmedsoft.popupdialog.Styles
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class TabFragment : BaseFragment<FragmentTabBinding>(R.layout.fragment_tab) {

    @Inject
    lateinit var sharedPrefHelper: SharedPrefHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_logout, menu)

    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                showLogoutDialog()
            }

            R.id.profile -> {
                findNavController().navigate(R.id.action_tabFragment_to_profileFragment)
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun showLogoutDialog() {
        PopupDialog.getInstance(requireContext())
            .setStyle(Styles.STANDARD)
            .setHeading(resources.getString(R.string.txt_logout))
            .setDescription(
                resources.getString(R.string.msg_logout)
            )
            .setPopupDialogIcon(R.drawable.ic_logout)
            .setPopupDialogIconTint(R.color.colorStatusBar)
            .setCancelable(false)
            .showDialog(object : OnDialogButtonClickListener() {
                override fun onPositiveClicked(dialog: Dialog) {
                    super.onPositiveClicked(dialog)
                    // finish the app logout means
                     FirebaseAuth.getInstance().signOut()
                    sharedPrefHelper.clearSharedPrefData()
                    requireActivity().finish()
                }

                override fun onNegativeClicked(dialog: Dialog) {
                    super.onNegativeClicked(dialog)
                }
            })
    }
}
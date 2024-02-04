package alkusi.mahato.e_commerce.screens.More

import alkusi.mahato.e_commerce.R
import alkusi.mahato.e_commerce.databinding.FragmentAboutUsBinding
import alkusi.mahato.e_commerce.library.views.BaseFragment
import androidx.appcompat.app.AppCompatActivity


class AboutUsFragment : BaseFragment<FragmentAboutUsBinding>(R.layout.fragment_about_us) {


    override fun FragmentAboutUsBinding.initialize() {
        setToolbar()
    }
    private fun setToolbar() {
        val activity = (requireActivity() as AppCompatActivity)
        activity.setSupportActionBar(binding.newToolbar.toolbar)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.title = "About us"
    }

}
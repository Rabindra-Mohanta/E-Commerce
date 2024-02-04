package alkusi.mahato.e_commerce.screens.More


import alkusi.mahato.e_commerce.R
import alkusi.mahato.e_commerce.databinding.FragmentContactUsBinding
import alkusi.mahato.e_commerce.library.views.BaseFragment
import androidx.appcompat.app.AppCompatActivity


class ContactUsFragment : BaseFragment<FragmentContactUsBinding>(R.layout.fragment_contact_us) {
    override fun FragmentContactUsBinding.initialize() {
        setToolbar()
    }
    private fun setToolbar() {
        val activity = (requireActivity() as AppCompatActivity)
        activity.setSupportActionBar(binding.newToolbar.toolbar)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.title = "Contact Us"
    }
}
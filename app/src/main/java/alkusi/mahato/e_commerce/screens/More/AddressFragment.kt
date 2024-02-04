package alkusi.mahato.e_commerce.screens.More

import alkusi.mahato.e_commerce.R
import alkusi.mahato.e_commerce.databinding.FragmentAddressBinding
import alkusi.mahato.e_commerce.library.views.BaseFragment
import androidx.appcompat.app.AppCompatActivity


class AddressFragment : BaseFragment<FragmentAddressBinding>(R.layout.fragment_address) {


    override fun FragmentAddressBinding.initialize() {
        setToolbar()
    }
    private fun setToolbar() {
        val activity = (requireActivity() as AppCompatActivity)
        activity.setSupportActionBar(binding.newToolbar.toolbar)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.title = "Address"
    }

}
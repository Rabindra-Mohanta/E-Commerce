package alkusi.mahato.e_commerce.screens.Shping.Fragments
import alkusi.mahato.e_commerce.Constants.MyConstants
import alkusi.mahato.e_commerce.R
import alkusi.mahato.e_commerce.databinding.FragmentShoppingBinding
import alkusi.mahato.e_commerce.library.views.BaseFragment
import alkusi.mahato.e_commerce.screens.NormalData
import alkusi.mahato.e_commerce.screens.ShoppingFragmentViewModel
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingFragment : BaseFragment<FragmentShoppingBinding>(R.layout.fragment_shopping) {
    var normalData:NormalData?=null
    val shoppingFragmentViewModel by  viewModels<ShoppingFragmentViewModel> ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        if(arguments!=null)
        {
            normalData = Gson().fromJson(arguments?.getString(MyConstants.data),NormalData::class.java)
        }
    }
    override fun FragmentShoppingBinding.initialize() {
        binding.viewModel = shoppingFragmentViewModel
        shoppingFragmentViewModel.normalData.set(normalData)
        setToolbar()
    }
    private fun setToolbar() {
        val activity = (requireActivity() as AppCompatActivity)
        activity.setSupportActionBar(binding.newToolbar.toolbar)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.title = "Product"
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                //this is for handle backPress
                requireActivity().onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
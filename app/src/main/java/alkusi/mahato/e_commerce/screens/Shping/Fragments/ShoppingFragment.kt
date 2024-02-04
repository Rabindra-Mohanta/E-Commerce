package alkusi.mahato.e_commerce.screens.Shping.Fragments
import alkusi.mahato.e_commerce.Constants.MyConstants
import alkusi.mahato.e_commerce.R
import alkusi.mahato.e_commerce.databinding.FragmentShoppingBinding
import alkusi.mahato.e_commerce.library.views.BaseFragment
import alkusi.mahato.e_commerce.screens.NormalData
import alkusi.mahato.e_commerce.screens.ShoppingFragmentViewModel
import android.app.Dialog
import android.graphics.Paint
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import com.saadahmedsoft.popupdialog.PopupDialog
import com.saadahmedsoft.popupdialog.Styles
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener
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
        shoppingFragmentViewModel.productOriginalPrice.value = normalData!!.originalPrice.toLong()
        shoppingFragmentViewModel.productOfferPrice.value = normalData!!.offerPrice.toLong()
        shoppingFragmentViewModel.normalData.set(normalData)
        setToolbar()
        initClicks()
        //set old amount stick
        binding.txtOldPrice.paintFlags  = Paint.STRIKE_THRU_TEXT_FLAG
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
    private fun initClicks()
    {
        binding.btnAddToCart.setOnClickListener {
            showAddCartDialog()
        }
        binding.btnBuyNow.setOnClickListener {
            showBuyDialog()
        }
    }
    private fun showAddCartDialog() {
        PopupDialog.getInstance(requireContext())
            .setStyle(Styles.STANDARD)
            .setHeading(requireContext().getString(R.string.txt_add_to_cart))
            .setDescription(
                resources.getString(R.string.msg_added_card)
            )
            .setPopupDialogIcon(R.drawable.icon_cart)
            .setPopupDialogIconTint(R.color.colorStatusBar)
            .setCancelable(false)
            .showDialog(object : OnDialogButtonClickListener() {
                override fun onPositiveClicked(dialog: Dialog) {
                    super.onPositiveClicked(dialog)
                    shoppingFragmentViewModel.callCartApi(normalData!!.title)

                }

                override fun onNegativeClicked(dialog: Dialog) {
                    super.onNegativeClicked(dialog)
                }
            })
    }
    private fun showBuyDialog() {
        if(shoppingFragmentViewModel.userAddress==null || shoppingFragmentViewModel.userAddress!!.isEmpty())
        {
            Toast.makeText(context,"Please update your profile before order ", Toast.LENGTH_SHORT)
                .show()
            return
        }
        PopupDialog.getInstance(requireContext())
            .setStyle(Styles.STANDARD)
            .setHeading(resources.getString(R.string.txt_buy_now))
            .setDescription(
                resources.getString(R.string.msg_added_buy)
            )
            .setPopupDialogIcon(R.drawable.icon_order)
            .setPopupDialogIconTint(R.color.colorStatusBar)
            .setCancelable(false)
            .showDialog(object : OnDialogButtonClickListener() {
                override fun onPositiveClicked(dialog: Dialog) {
                    super.onPositiveClicked(dialog)
                    shoppingFragmentViewModel.callBuyDataApi(normalData!!.title)

                }

                override fun onNegativeClicked(dialog: Dialog) {
                    super.onNegativeClicked(dialog)
                }
            })
    }
}
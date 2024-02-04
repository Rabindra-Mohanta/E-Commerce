package alkusi.mahato.e_commerce.screens.Cart.fragments

import alkusi.mahato.e_commerce.R
import alkusi.mahato.e_commerce.databinding.FragmentCartBinding
import alkusi.mahato.e_commerce.library.views.BaseFragment
import alkusi.mahato.e_commerce.screens.CardViewModel
import alkusi.mahato.e_commerce.screens.Cart.Adapters.CartAdapter
import alkusi.mahato.e_commerce.screens.NormalData
import android.app.Dialog
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.saadahmedsoft.popupdialog.PopupDialog
import com.saadahmedsoft.popupdialog.Styles
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CartFragment : BaseFragment<FragmentCartBinding>(R.layout.fragment_cart) {
    val cartViewModel by viewModels<CardViewModel> ()
    override fun FragmentCartBinding.initialize() {
   binding.viewModel = cartViewModel
        observeData()
        initClicks()
    }

    override fun onResume() {
        super.onResume()
        requireActivity().title = resources.getString(R.string.txt_cart)

    }
    private fun observeData() {
        cartViewModel.cartDataList.observe(this) {
            if (it.isEmpty())
                cartViewModel.isNodataFound.set(true)
            else
                cartViewModel.isNodataFound.set(false)
            binding.swipeRefreshLayout.isRefreshing = false
            initRv(it)
        }
    }

    private fun initClicks()
    {
        binding.swipeRefreshLayout.setOnRefreshListener {
            cartViewModel.getCartData()
        }
    }

    private fun initRv(list: List<NormalData>) {
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        val adapter =
            CartAdapter(list){
                showRemoveDialog(it)
            }
        binding.recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun showRemoveDialog(productTitle:String) {
        PopupDialog.getInstance(requireContext())
            .setStyle(Styles.STANDARD)
            .setHeading(resources.getString(R.string.txt_remove))
            .setDescription(
                resources.getString(R.string.msg_remove_dialog)
            )
            .setPopupDialogIcon(R.drawable.icon_remove_cart)
            .setPopupDialogIconTint(R.color.colorStatusBar)
            .setCancelable(false)
            .showDialog(object : OnDialogButtonClickListener() {
                override fun onPositiveClicked(dialog: Dialog) {
                    super.onPositiveClicked(dialog)
                    cartViewModel.removeFromCart(productTitle)
                }

                override fun onNegativeClicked(dialog: Dialog) {
                    super.onNegativeClicked(dialog)
                }
            })
    }
}
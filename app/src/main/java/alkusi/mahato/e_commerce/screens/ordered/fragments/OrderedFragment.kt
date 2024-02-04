package alkusi.mahato.e_commerce.screens.ordered.fragments

import alkusi.mahato.e_commerce.R
import alkusi.mahato.e_commerce.databinding.FragmentOrderedBinding
import alkusi.mahato.e_commerce.library.views.BaseFragment
import alkusi.mahato.e_commerce.screens.NormalData
import alkusi.mahato.e_commerce.screens.OrderedViewModel
import alkusi.mahato.e_commerce.screens.ordered.Adapter.OrderedAdapter
import android.app.Dialog
import androidx.fragment.app.viewModels
import com.saadahmedsoft.popupdialog.PopupDialog
import com.saadahmedsoft.popupdialog.Styles
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OrderedFragment : BaseFragment<FragmentOrderedBinding>(R.layout.fragment_ordered) {
    val orderedViewModel by viewModels<OrderedViewModel>()
    override fun FragmentOrderedBinding.initialize() {
        binding.viewModel = orderedViewModel
        observeData()
        initClicks()
    }

    private fun observeData() {
        orderedViewModel.orderedDataList.observe(this) {
            if (it.isEmpty())
                orderedViewModel.isNodataFound.set(true)
            else
                orderedViewModel.isNodataFound.set(false)
            binding.swipeRefreshLayout.isRefreshing = false
            initRv(it)
        }
    }

    private fun initClicks() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            orderedViewModel.getOrderedData()
        }
    }

    private fun initRv(list: List<NormalData>) {
        binding.recyclerView.setHasFixedSize(true)
        val orderedAdapter = OrderedAdapter(list) {
            showCancelDialog(it)
        }
        binding.recyclerView.adapter = orderedAdapter
        orderedAdapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        requireActivity().title = resources.getString(R.string.txt_ordered)
        //get ordered data

    }

    private fun showCancelDialog(productTitle: String) {
        PopupDialog.getInstance(requireContext())
            .setStyle(Styles.STANDARD)
            .setHeading(resources.getString(R.string.txt_cancel))
            .setDescription(
                resources.getString(R.string.msg_cancel_order)
            )
            .setPopupDialogIcon(R.drawable.icon_remove_cart)
            .setPopupDialogIconTint(R.color.colorStatusBar)
            .setCancelable(false)
            .showDialog(object : OnDialogButtonClickListener() {
                override fun onPositiveClicked(dialog: Dialog) {
                    super.onPositiveClicked(dialog)
                    orderedViewModel.cancelOrder(productTitle)
                }

                override fun onNegativeClicked(dialog: Dialog) {
                    super.onNegativeClicked(dialog)
                }
            })
    }
}
package alkusi.mahato.e_commerce.screens.Cart.fragments

import alkusi.mahato.e_commerce.Constants.MyConstants
import alkusi.mahato.e_commerce.R
import alkusi.mahato.e_commerce.databinding.FragmentCartBinding
import alkusi.mahato.e_commerce.library.views.BaseFragment
import alkusi.mahato.e_commerce.screens.CardViewModel
import alkusi.mahato.e_commerce.screens.Cart.Adapters.CartAdapter
import alkusi.mahato.e_commerce.screens.Home.Adapters.NormalDataAdapter
import alkusi.mahato.e_commerce.screens.NormalData
import alkusi.mahato.e_commerce.screens.ordered.Adapter.OrderedAdapter
import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CartFragment : BaseFragment<FragmentCartBinding>(R.layout.fragment_cart) {
    val cartViewModel by viewModels<CardViewModel> ()
    override fun FragmentCartBinding.initialize() {
   binding.viewModel = cartViewModel
        observeData()
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
            initRv(it)
        }
    }

    private fun initRv(list: List<NormalData>) {
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        val adapter =
            CartAdapter(list)
        binding.recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }


}
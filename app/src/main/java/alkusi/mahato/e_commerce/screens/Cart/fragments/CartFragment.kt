package alkusi.mahato.e_commerce.screens.Cart.fragments

import alkusi.mahato.e_commerce.R
import alkusi.mahato.e_commerce.databinding.FragmentCartBinding
import alkusi.mahato.e_commerce.library.views.BaseFragment


class CartFragment : BaseFragment<FragmentCartBinding>(R.layout.fragment_cart) {
    override fun FragmentCartBinding.initialize() {

    }

    override fun onResume() {
        super.onResume()
        requireActivity().title = resources.getString(R.string.txt_cart)
    }

}
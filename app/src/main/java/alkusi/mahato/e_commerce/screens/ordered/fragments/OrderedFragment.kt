package alkusi.mahato.e_commerce.screens.ordered.fragments

import alkusi.mahato.e_commerce.R
import alkusi.mahato.e_commerce.databinding.FragmentOrderedBinding
import alkusi.mahato.e_commerce.library.views.BaseFragment
import alkusi.mahato.e_commerce.screens.NormalData
import alkusi.mahato.e_commerce.screens.OrderedViewModel
import alkusi.mahato.e_commerce.screens.ordered.Adapter.OrderedAdapter
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OrderedFragment : BaseFragment<FragmentOrderedBinding>(R.layout.fragment_ordered) {
    val orderedViewModel by viewModels<OrderedViewModel>()
    override fun FragmentOrderedBinding.initialize() {
        binding.viewModel = orderedViewModel
        observeData()
    }

    private fun observeData() {
        orderedViewModel.orderedDataList.observe(this) {
            if (it.isEmpty())
                orderedViewModel.isNodataFound.set(true)
            else
                orderedViewModel.isNodataFound.set(false)
            initRv(it)
        }
    }

    private fun initRv(list: List<NormalData>) {
        binding.recyclerView.setHasFixedSize(true)
        val orderedAdapter = OrderedAdapter(list) {

        }
        binding.recyclerView.adapter = orderedAdapter
        orderedAdapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        requireActivity().title = resources.getString(R.string.txt_ordered)
    }

}
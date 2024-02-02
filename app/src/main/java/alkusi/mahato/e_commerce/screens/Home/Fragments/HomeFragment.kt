package alkusi.mahato.e_commerce.screens.Home.Fragments

import alkusi.mahato.e_commerce.R
import alkusi.mahato.e_commerce.databinding.FragmentHomeBinding
import alkusi.mahato.e_commerce.library.views.BaseFragment
import alkusi.mahato.e_commerce.screens.Home.Adapters.ElectronicsDataAdapter
import alkusi.mahato.e_commerce.screens.HomeViewModel
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val homeViewModel by viewModels<HomeViewModel>()
    override fun FragmentHomeBinding.initialize() {
        binding.viewModel = homeViewModel
        init()
        setElectronicRv()
    }

    override fun onResume() {
        super.onResume()
        requireActivity().title = resources.getString(R.string.txt_home)
    }

private fun setElectronicRv()
{
    binding.recyclerView.setHasFixedSize(true)
    binding.recyclerView.layoutManager = GridLayoutManager(requireContext(),2,GridLayoutManager.VERTICAL,false)
    val adapter = ElectronicsDataAdapter(homeViewModel.loadElectronicsDataFromAssets().electronics)
    binding.recyclerView.adapter = adapter
    adapter.notifyDataSetChanged()
}


private fun init()
{
    binding.txtElectronics.setBackgroundColor(resources.getColor(R.color.filter_bg_color))
    binding.txtMale.background = resources.getDrawable(R.drawable.gender_filter_bg)
    binding.txtFemale.background = resources.getDrawable(R.drawable.gender_filter_bg)


    binding.txtElectronics.setOnClickListener {
        binding.txtElectronics.setBackgroundColor(resources.getColor(R.color.filter_bg_color))
        binding.txtMale.background = resources.getDrawable(R.drawable.gender_filter_bg)
        binding.txtFemale.background = resources.getDrawable(R.drawable.gender_filter_bg)
    }
    binding.txtMale.setOnClickListener {
        binding.txtElectronics.background = resources.getDrawable(R.drawable.gender_filter_bg)
        binding.txtMale.setBackgroundColor(resources.getColor(R.color.filter_bg_color))
        binding.txtFemale.background = resources.getDrawable(R.drawable.gender_filter_bg)

    }
    binding.txtFemale.setOnClickListener {
        binding.txtElectronics.background = resources.getDrawable(R.drawable.gender_filter_bg)
        binding.txtMale.background = resources.getDrawable(R.drawable.gender_filter_bg)
        binding.txtFemale.setBackgroundColor(resources.getColor(R.color.filter_bg_color))


    }

}
}
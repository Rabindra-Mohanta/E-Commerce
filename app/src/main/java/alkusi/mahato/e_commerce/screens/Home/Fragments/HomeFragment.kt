package alkusi.mahato.e_commerce.screens.Home.Fragments

import alkusi.mahato.e_commerce.R
import alkusi.mahato.e_commerce.databinding.FragmentHomeBinding
import alkusi.mahato.e_commerce.library.views.BaseFragment


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override fun FragmentHomeBinding.initialize() {
        init()
    }

    override fun onResume() {
        super.onResume()
        requireActivity().title = resources.getString(R.string.txt_home)
    }
private fun init()
{
    binding.txtAll.setBackgroundColor(resources.getColor(R.color.filter_bg_color))
    binding.txtMale.background = resources.getDrawable(R.drawable.gender_filter_bg)
    binding.txtFemale.background = resources.getDrawable(R.drawable.gender_filter_bg)


    binding.txtAll.setOnClickListener {
        binding.txtAll.setBackgroundColor(resources.getColor(R.color.filter_bg_color))
        binding.txtMale.background = resources.getDrawable(R.drawable.gender_filter_bg)
        binding.txtFemale.background = resources.getDrawable(R.drawable.gender_filter_bg)
    }
    binding.txtMale.setOnClickListener {
        binding.txtAll.background = resources.getDrawable(R.drawable.gender_filter_bg)
        binding.txtMale.setBackgroundColor(resources.getColor(R.color.filter_bg_color))
        binding.txtFemale.background = resources.getDrawable(R.drawable.gender_filter_bg)

    }
    binding.txtFemale.setOnClickListener {
        binding.txtAll.background = resources.getDrawable(R.drawable.gender_filter_bg)
        binding.txtMale.background = resources.getDrawable(R.drawable.gender_filter_bg)
        binding.txtFemale.setBackgroundColor(resources.getColor(R.color.filter_bg_color))


    }

}
}
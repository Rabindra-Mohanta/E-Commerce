package alkusi.mahato.e_commerce.screens.Auth.Fragments

import alkusi.mahato.e_commerce.R
import alkusi.mahato.e_commerce.databinding.FragmentSignupBinding
import alkusi.mahato.e_commerce.library.views.BaseFragment
import alkusi.mahato.e_commerce.screens.SignupViewModel
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFragment : BaseFragment<FragmentSignupBinding>(R.layout.fragment_signup) {
    val signUpViewModel by viewModels<SignupViewModel>()
    override fun FragmentSignupBinding.initialize() {
        binding.viewModel = signUpViewModel
        observeData()
    }

    private fun observeData() {
        signUpViewModel.isNavigateToHome.observe(viewLifecycleOwner) {
            if (it) {
                signUpViewModel.isNavigateToHome.value = false
                findNavController().navigate(R.id.action_signupFragment_to_tabFragment)
            }
        }
    }


}
package alkusi.mahato.e_commerce.screens.Auth.Fragments
import alkusi.mahato.e_commerce.R
import alkusi.mahato.e_commerce.databinding.FragmentLoginBinding
import alkusi.mahato.e_commerce.screens.LoginViewModel
import alkusi.mahato.e_commerce.library.views.BaseFragment
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
  val viewModel by viewModels<LoginViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        //observe data
        observeData()
    }
    private fun observeData()
    {
        viewModel.isNavigateToHome.observe(viewLifecycleOwner){
            if(it)
            {
                viewModel.isNavigateToHome.value = false
                findNavController().navigate(R.id.action_loginFragment_to_tabFragment)

            }
        }
        viewModel.isNavigateToNewAccount.observe(viewLifecycleOwner){
            if(it)
            {
                viewModel.isNavigateToNewAccount.value = false
                findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
            }

        }
    }

}
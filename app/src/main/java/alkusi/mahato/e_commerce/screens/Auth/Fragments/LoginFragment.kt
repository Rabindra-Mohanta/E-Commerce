package alkusi.mahato.e_commerce.screens.Auth.Fragments

import alkusi.mahato.e_commerce.R
import alkusi.mahato.e_commerce.databinding.FragmentLoginBinding
import alkusi.mahato.e_commerce.datahelper.SharedPrefHelper
import alkusi.mahato.e_commerce.screens.LoginViewModel
import alkusi.mahato.e_commerce.library.views.BaseFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    val viewModel1 by viewModels<LoginViewModel>()
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var firebaseFirestore: FirebaseFirestore

    @Inject
    lateinit var sharedPrefHelper: SharedPrefHelper

    override fun FragmentLoginBinding.initialize() {
        binding.viewModel = viewModel1
        //observe data
        observeData()
        initFireStore()
    }

    private fun initFireStore() {
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseFirestore = FirebaseFirestore.getInstance()
        if (firebaseAuth.currentUser != null) {
            findNavController().navigate(R.id.action_loginFragment_to_tabFragment)
        }
    }

    private fun observeData() {
        viewModel1.isNavigateToHome.observe(viewLifecycleOwner) {
            if (it) {
                viewModel1.isNavigateToHome.value = false
                findNavController().navigate(R.id.action_loginFragment_to_tabFragment)

            }
        }
        viewModel1.isNavigateToNewAccount.observe(viewLifecycleOwner) {
            if (it) {
                viewModel1.isNavigateToNewAccount.value = false
                // Add the login fragment to the back stack
                findNavController().navigate(R.id.action_loginFragment_to_signupFragment)

            }

        }
    }

}
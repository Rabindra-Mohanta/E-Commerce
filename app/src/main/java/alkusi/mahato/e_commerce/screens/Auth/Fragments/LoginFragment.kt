package alkusi.mahato.e_commerce.screens.Auth.Fragments
import alkusi.mahato.e_commerce.R
import alkusi.mahato.e_commerce.databinding.FragmentLoginBinding
import alkusi.mahato.e_commerce.screens.LoginViewModel
import alkusi.mahato.e_commerce.library.views.BaseFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
  val viewModel1 by viewModels<LoginViewModel>()
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var firebaseFirestore: FirebaseFirestore

    override fun FragmentLoginBinding.initialize() {
       binding.viewModel = viewModel1
        //observe data
        observeData()
        initFireStore()
    }






    private fun initFireStore()
    {
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseFirestore = FirebaseFirestore.getInstance()
        if(firebaseAuth.currentUser != null)
        {
            findNavController().navigate(R.id.action_loginFragment_to_tabFragment)
        }
    }

    private fun observeData()
    {
        viewModel1.isNavigateToHome.observe(viewLifecycleOwner){
            if(it)
            {
                viewModel1.isNavigateToHome.value = false
                findNavController().navigate(R.id.action_loginFragment_to_tabFragment)

            }
        }
        viewModel1.isNavigateToNewAccount.observe(viewLifecycleOwner){
            if(it)
            {
                viewModel1.isNavigateToNewAccount.value = false
                findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
            }

        }
    }

}
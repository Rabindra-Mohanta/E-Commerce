package alkusi.mahato.e_commerce.screens.Profile.fragments

import alkusi.mahato.e_commerce.Constants.MyConstants
import alkusi.mahato.e_commerce.R
import alkusi.mahato.e_commerce.databinding.FragmentProfileBinding
import alkusi.mahato.e_commerce.library.views.BaseFragment
import alkusi.mahato.e_commerce.screens.ProfileDataRes
import alkusi.mahato.e_commerce.screens.ProfileViewModel
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    private val profileViewModel by viewModels<ProfileViewModel>()
    var profileUri:Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun FragmentProfileBinding.initialize() {
        binding.viewModel = profileViewModel
        permission()
        setToolbar()
        observeData()
        initClicks()
    }

    private fun observeData() {
        profileViewModel.isUpdatedProfile.observe(this)
        {
            if (it) {
                requireActivity().onBackPressed()
            }
        }
    }

    private fun initClicks() {
        binding.btnSubmit.setOnClickListener {
            val profileDataRes = profileViewModel.profileDataRes.get()
            profileDataRes?.address = binding.edtAddress.text.toString()
            profileDataRes?.gender = binding.edtGender.text.toString()
            profileDataRes?.phoneNumber = binding.edtPhone.text.toString()
            profileDataRes?.userName = binding.edtUserName.text.toString()
            profileUri?.let {
                val imageName = "image"+"profile"+System.currentTimeMillis()
                profileViewModel.uploadImage(it,imageName)
                profileDataRes?.image = imageName
            }
            profileViewModel.updateProfileData(profileDataRes!!)
        }

        binding.imageView.setOnClickListener {
            val intent = Intent()
            intent.setType("image/*")
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(
                Intent.createChooser(intent, "Select Picture"),
                MyConstants.PICK_IMAGE
            )

        }
    }

    private fun setToolbar() {
        val activity = (requireActivity() as AppCompatActivity)
        activity.setSupportActionBar(binding.newToolbar.toolbar)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.title = resources.getString(R.string.txt_my_profile)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                //this is for handle backPress
                requireActivity().onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MyConstants.PICK_IMAGE) {
            if (resultCode == RESULT_OK) {
                val selectedImageUri: Uri? = data?.data
                profileUri = selectedImageUri
                binding.imageView.setImageURI(selectedImageUri)
            }
        }
    }

    private fun permission() {
        val requestPermissions =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { results ->
                // Handle permission requests results
                // See the permission example in the Android platform samples: https://github.com/android/platform-samples
            }
        if (
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE &&
            ContextCompat.checkSelfPermission(
                requireContext(),
                READ_MEDIA_VISUAL_USER_SELECTED
            ) != PERMISSION_GRANTED
        ) {
            requestPermissions.launch(arrayOf(READ_MEDIA_VISUAL_USER_SELECTED))
            // Partial access on Android 14 (API level 34) or higher
        } else if (
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            (ContextCompat.checkSelfPermission(
                requireContext(),
                READ_MEDIA_IMAGES
            ) != PERMISSION_GRANTED)
        ) {
            requestPermissions.launch(arrayOf(READ_MEDIA_IMAGES))
        } else if (ContextCompat.checkSelfPermission(
                requireContext(),
                READ_EXTERNAL_STORAGE
            ) != PERMISSION_GRANTED
        ) {
            requestPermissions.launch(arrayOf(READ_EXTERNAL_STORAGE))
            // Full access up to Android 12 (API level 32)
        } else {
            // Access denied
        }
    }
}
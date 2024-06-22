package com.yudhi.moviedatabase.presentation.ui

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.yudhi.moviedatabase.R
import com.yudhi.moviedatabase.databinding.FragmentProfileBinding
import com.yudhi.domain.helper.MyDataStore
import com.yudhi.moviedatabase.presentation.viewmodel.BlurViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var profileImageView: ImageView
    private val blurViewModel: BlurViewModel by viewModel()
    private val MyDataStore: MyDataStore by inject()


    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            profileImageView.setImageURI(it)
            blurViewModel.setImageUri(it)
            blurViewModel.applyBlur()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileImageView = binding.ivLogo
        val usernameEditText = binding.etUsername.editText
        val passwordEditText = binding.etPassword.editText
        val passwordEditText2 = binding.etPassword2.editText
        val emailEditText = binding.etEmail.editText
        viewLifecycleOwner.lifecycleScope.launch {
            MyDataStore.getSavedAccount().collect { (username, password, email) ->
                usernameEditText?.setText(username)
                passwordEditText?.setText(password)
                passwordEditText2?.setText(password)
                emailEditText?.setText(email)
            }
        }
        lifecycleScope.launch {
            MyDataStore.getFoto().collect { foto ->
                foto?.let {
                    profileImageView.setImageURI(Uri.parse(it))
                }
            }
        }

        binding.btnUpdate.setOnClickListener {
            val enteredUsername = usernameEditText?.text.toString()
            val enteredPassword = passwordEditText?.text.toString()
            val enteredPassword2 = passwordEditText2?.text.toString()
            val enteredEmail = emailEditText?.text.toString()

            // Perform some validation here if needed
            if (enteredPassword == enteredPassword2) {
                // Save the account information
                lifecycleScope.launch {
                    MyDataStore.saveAccount( enteredUsername, enteredPassword, enteredEmail)
                }

            } else {
                Toast.makeText(requireContext(), "WRONG PASSWORD", Toast.LENGTH_SHORT).show()
            }
            view.findNavController().navigate(R.id.action_profileFragment_to_movieFragment)

        }

        binding.btnLogout.setOnClickListener{
            //SharedPreference.clearAccount(requireContext())
            view.findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }

        profileImageView.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

    }
}
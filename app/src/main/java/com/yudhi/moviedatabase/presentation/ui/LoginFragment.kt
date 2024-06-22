package com.yudhi.moviedatabase.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.yudhi.moviedatabase.R
import com.yudhi.moviedatabase.databinding.FragmentLoginBinding
import com.yudhi.domain.helper.MyDataStore

import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class LoginFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentLoginBinding
    private val MyDataStore: MyDataStore by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            MyDataStore.getLogin().collect { is_login_key->
                if (is_login_key == true) {
                    view.findNavController().navigate(R.id.action_loginFragment_to_movieFragment)
                    Toast.makeText(requireContext(), "Succses Login", Toast.LENGTH_SHORT).show()
                    Log.d("d", "Navigation to Movie Fragment")
                } else {
                    val usernameEditText = binding.etUsername.editText
                    val passwordEditText = binding.etPassword.editText

                    binding.btnLoginMovie.setOnClickListener {
                        val enteredUsername = usernameEditText?.text.toString()
                        val enteredPassword = passwordEditText?.text.toString()
                        viewLifecycleOwner.lifecycleScope.launch {
                            MyDataStore.getSavedAccount().collect { (username, password) ->
                                if (username == enteredUsername && password == enteredPassword) {
                                    view.findNavController().navigate(R.id.action_loginFragment_to_movieFragment)
                                    Toast.makeText(requireContext(), "succses", Toast.LENGTH_SHORT).show()
                                    Log.d("d", "Navigation to Movie Fragment")
                                } else {
                                    Toast.makeText(requireContext(), "INVALID LOGIN", Toast.LENGTH_SHORT).show()
                                    Log.d("d", "cantt")
                                }
                            }
                        }



                    }

                    binding.tvAkun.setOnClickListener{
                        view.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
                    }
                    Log.d("d", "normal")
                }
            }
        }


    }




}



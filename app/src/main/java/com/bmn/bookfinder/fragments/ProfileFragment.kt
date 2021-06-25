package com.bmn.bookfinder.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bmn.bookfinder.R
import com.bmn.bookfinder.activities.MainActivity
import com.bmn.bookfinder.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentProfileBinding.inflate(inflater)
        if (FirebaseAuth.getInstance().uid == null || FirebaseAuth.getInstance().uid?.isEmpty() == true) {
            binding.noAccountView.visibility = View.VISIBLE
            binding.editProfile.visibility = View.GONE
            binding.profileImage.visibility = View.GONE
        }
        binding.signinButton.setOnClickListener(this)
        binding.signupButton.setOnClickListener(this)
        binding.logOut.setOnClickListener(this)
        return binding.root
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.signup_button ->
                findNavController().navigate(
                    ProfileFragmentDirections.actionProfileFragmentToSignUpFragment()
                )
            R.id.signin_button ->
                findNavController().navigate(
                    ProfileFragmentDirections.actionProfileFragmentToLoginFragment()
                )
            R.id.log_out -> {
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(context, MainActivity::class.java))
            }
        }
    }
}
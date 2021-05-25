package com.bmn.bookfinder.fragments

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bmn.bookfinder.R
import com.bmn.bookfinder.activities.MainActivity
import com.bmn.bookfinder.databinding.FragmentSignUpBinding
import com.bmn.bookfinder.utils.Utils
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment : Fragment(), View.OnClickListener {
    private var bundle: Bundle? = null
    private var progressDialog: ProgressDialog? = null
    private var binding: FragmentSignUpBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bundle = arguments
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        progressDialog = ProgressDialog(context)
        progressDialog!!.setMessage(getString(R.string.signing_up))
        progressDialog!!.setCancelable(false)
        binding = FragmentSignUpBinding.inflate(inflater)
        setOnClickListeners(binding!!.signUpButton)
        return binding!!.root
    }

    private fun setOnClickListeners(vararg views: View) {
        for (view in views) {
            view.setOnClickListener(this)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.sign_up_button -> createAccount()
        }
    }

    private fun createAccount() {
        val email = binding!!.signUpEmail.text.toString()
        val password = binding!!.signUpPassword.text.toString()
        val conPassword = binding!!.signUpConfirmPassword.text.toString()
        if (validate(email, password, conPassword)) {
            progressDialog!!.show()
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task: Task<AuthResult?> ->
                    if (task.isSuccessful) {
                        progressDialog!!.cancel()
                        Toast.makeText(
                            context,
                            R.string.account_created_successfully,
                            Toast.LENGTH_SHORT
                        ).show()
                        startActivity(Intent(context, MainActivity::class.java))
                    } else {
                        Toast.makeText(
                            context,
                            R.string.account_creation_failed,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    private fun validate(email: String, password: String, conPassword: String): Boolean {
        if (!Utils.isValidEmail(email)) {
            binding!!.signUpEmail.error = getString(R.string.invalid_email_address)
            return false
        }
        if (email.isEmpty()) {
            binding!!.signUpEmail.error = getString(R.string.email_is_required)
            binding!!.signUpEmail.requestFocus()
            return false
        }
        if (password.isEmpty()) {
            binding!!.signUpPassword.error = getString(R.string.password_is_required)
            binding!!.signUpPassword.requestFocus()
            return false
        }
        if (conPassword.isEmpty()) {
            binding!!.signUpConfirmPassword.error = getString(R.string.confirm_password_is_required)
            binding!!.signUpConfirmPassword.requestFocus()
            return false
        }
        if (conPassword != password) {
            binding!!.signUpPassword.error = getString(R.string.passwords_do_not_match)
            return false
        }
        if (password.length < 6) {
            binding!!.signUpPassword.error = getString(R.string.your_password_must_be_6_long)
            return false
        }
        if (!binding!!.termsAndConditions.isChecked) {
            binding!!.termsAndConditions.error =
                getString(R.string.cannot_create_account_without_terms_conditions)
            binding!!.termsAndConditions.requestFocus()
            return false
        }
        return true
    }
}
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
import com.bmn.bookfinder.databinding.FragmentLoginBinding
import com.bmn.bookfinder.utils.Utils
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment(), View.OnClickListener {
    private var firebaseAuth: FirebaseAuth? = null
    private var progressDialog: ProgressDialog? = null
    private var binding: FragmentLoginBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        progressDialog = ProgressDialog(context)
        progressDialog!!.setMessage(getString(R.string.logging_in))
        progressDialog!!.setCancelable(false)
        setOnClickListeners(binding!!.loginButton)
        return binding!!.root
    }

    private fun setOnClickListeners(vararg views: View) {
        for (view in views) {
            view.setOnClickListener(this)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.login_button -> login()
        }
    }

    private fun login() {
        val email = binding!!.loginEmail.text.toString()
        val password = binding!!.loginPassword.text.toString()
        if (validate(email, password)) {
            progressDialog!!.show()
            firebaseAuth!!.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task: Task<AuthResult?> ->
                    if (task.isSuccessful) {
                        startActivity(Intent(context, MainActivity::class.java))
                    } else {
                        progressDialog!!.hide()
                        Toast.makeText(context, R.string.authorization_failed, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
        }
    }

    private fun validate(email: String, password: String): Boolean {
        if (!Utils.isValidEmail(email)) {
            binding!!.loginEmail.error = getString(R.string.invalid_email_address)
            binding!!.loginEmail.requestFocus()
            return false
        }
        if (email.isEmpty()) {
            binding!!.loginEmail.error = getString(R.string.email_is_required)
            binding!!.loginEmail.requestFocus()
            return false
        }
        if (password.isEmpty()) {
            binding!!.loginPassword.error = getString(R.string.password_is_required)
            binding!!.loginPassword.requestFocus()
            return false
        }
        if (password.length < 6) {
            binding!!.loginPassword.error = getString(R.string.your_password_must_be_6_long)
            binding!!.loginPassword.requestFocus()
            return false
        }
        return true
    }
}
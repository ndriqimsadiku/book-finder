package com.bmn.bookfinder.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bmn.bookfinder.R;
import com.bmn.bookfinder.activities.MainActivity;
import com.bmn.bookfinder.databinding.FragmentLoginBinding;
import com.google.firebase.auth.FirebaseAuth;

import static com.bmn.bookfinder.utils.Utils.isValidEmail;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {


    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private FragmentLoginBinding binding;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getString(R.string.logging_in));
        progressDialog.setCancelable(false);

        setOnClickListeners(binding.loginButton);

        return binding.getRoot();
    }

    private void setOnClickListeners(View... views) {
        for (View view : views) {
            view.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.login_button):
                login();
                break;
        }
    }

    private void login() {
        String email = binding.loginEmail.getText().toString();
        String password = binding.loginPassword.getText().toString();

        if (validate(email, password)) {
            progressDialog.show();
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    startActivity(new Intent(getContext(), MainActivity.class));
                } else {
                    progressDialog.hide();
                    Toast.makeText(getContext(), R.string.authorization_failed, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private boolean validate(String email, String password) {

        if (!isValidEmail(email)) {
            binding.loginEmail.setError(getString(R.string.invalid_email_address));
            binding.loginEmail.requestFocus();
            return false;
        }

        if (email.isEmpty()) {
            binding.loginEmail.setError(getString(R.string.email_is_required));
            binding.loginEmail.requestFocus();
            return false;
        }

        if (password.isEmpty()) {
            binding.loginPassword.setError(getString(R.string.password_is_required));
            binding.loginPassword.requestFocus();
            return false;
        }

        if (password.length() < 6) {
            binding.loginPassword.setError(getString(R.string.your_password_must_be_6_long));
            binding.loginPassword.requestFocus();
            return false;
        }
        return true;
    }
}

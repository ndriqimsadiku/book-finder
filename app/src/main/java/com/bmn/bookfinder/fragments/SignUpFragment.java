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
import com.bmn.bookfinder.databinding.FragmentSignUpBinding;
import com.bmn.bookfinder.helpers.FragmentHelper;
import com.google.firebase.auth.FirebaseAuth;

import static com.bmn.bookfinder.utils.Utils.isValidEmail;


public class SignUpFragment extends Fragment implements View.OnClickListener {

    private Bundle bundle;
    private ProgressDialog progressDialog;

    private FragmentSignUpBinding binding;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getString(R.string.signing_up));
        progressDialog.setCancelable(false);

        binding = FragmentSignUpBinding.inflate(inflater);
        setOnClickListeners(binding.signUpButton, binding.login);

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

            case (R.id.sign_up_button):
                createAccount();
                break;
            case (R.id.login):
                LoginFragment loginFragment = new LoginFragment();
                FragmentHelper.changeFragment(getParentFragmentManager(), loginFragment);
                break;
        }
    }

    private void createAccount() {
        String email = binding.signUpEmail.getText().toString();
        String password = binding.signUpPassword.getText().toString();
        String conPassword = binding.signUpConfirmPassword.getText().toString();
        if (validate(email, password, conPassword)) {
            progressDialog.show();
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    progressDialog.cancel();
                    Toast.makeText(getContext(), R.string.account_created_successfully, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(), MainActivity.class));
                } else {
                    Toast.makeText(getContext(), R.string.account_creation_failed, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    private boolean validate(String email, String password, String conPassword) {

        if (!isValidEmail(email)) {
            binding.signUpEmail.setError(getString(R.string.invalid_email_address));
            return false;
        }

        if (email.isEmpty()) {
            binding.signUpEmail.setError(getString(R.string.email_is_required));
            binding.signUpEmail.requestFocus();
            return false;
        }

        if (password.isEmpty()) {
            binding.signUpPassword.setError(getString(R.string.password_is_required));
            binding.signUpPassword.requestFocus();
            return false;
        }

        if (conPassword.isEmpty()) {
            binding.signUpConfirmPassword.setError(getString(R.string.confirm_password_is_required));
            binding.signUpConfirmPassword.requestFocus();
            return false;
        }

        if (!conPassword.equals(password)) {
            binding.signUpPassword.setError(getString(R.string.passwords_do_not_match));
            return false;
        }

        if (password.length() < 6) {
            binding.signUpPassword.setError(getString(R.string.your_password_must_be_6_long));
            return false;
        }
        if (!binding.termsAndConditions.isChecked()) {
            binding.termsAndConditions.setError(getString(R.string.cannot_create_account_without_terms_conditions));
            binding.termsAndConditions.requestFocus();
            return false;
        }
        return true;
    }
}
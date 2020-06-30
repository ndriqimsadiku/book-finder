package com.bmn.bookfinder.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.bmn.bookfinder.R;
import com.bmn.bookfinder.activities.AccountActivity;
import com.bmn.bookfinder.activities.MainActivity;
import com.bmn.bookfinder.databinding.FragmentProfileBinding;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private FragmentProfileBinding binding;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater);
        if (FirebaseAuth.getInstance().getUid() == null || FirebaseAuth.getInstance().getUid().isEmpty()) {
            binding.noAccountView.setVisibility(View.VISIBLE);
            binding.editProfile.setVisibility(View.GONE);
            binding.profileImage.setVisibility(View.GONE);
        }

        binding.signinButton.setOnClickListener(this);
        binding.signupButton.setOnClickListener(this);
        binding.logOut.setOnClickListener(this);

        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signup_button:
            case R.id.signin_button:
                startActivity(new Intent(getContext(), AccountActivity.class));
                break;
            case R.id.log_out:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(), MainActivity .class));
        }
    }
}
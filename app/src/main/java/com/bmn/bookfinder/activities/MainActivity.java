package com.bmn.bookfinder.activities;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.bmn.bookfinder.R;
import com.bmn.bookfinder.data.network.remote.ApiUtils;
import com.bmn.bookfinder.databinding.ActivityMainBinding;
import com.bmn.bookfinder.helpers.Constants;
import com.bmn.bookfinder.models.NYTimesResponse;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.SOFT_INPUT_MASK_ADJUST
        );

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initData();

        ApiUtils.getNYTimesApiService(getApplicationContext()).getBestSellers(
                Constants.NYTimes.HARDCOVER_FICTION,
                Constants.NYTimes.NY_API_KEY
        ).enqueue(new Callback<NYTimesResponse>() {
            @Override
            public void onResponse(@NotNull Call<NYTimesResponse> call, @NotNull Response<NYTimesResponse> response) {
                System.out.println(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<NYTimesResponse> call, @NotNull Throwable t) {
                System.out.println(t.toString());
            }
        });
    }

    private void initData() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(binding.navigation, Objects.requireNonNull(navHostFragment).getNavController()
        );
    }
}

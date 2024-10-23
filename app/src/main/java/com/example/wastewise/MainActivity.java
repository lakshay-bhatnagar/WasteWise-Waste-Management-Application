package com.example.wastewise;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.wastewise.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    // Declare an instance of ActivityMainBinding
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize binding by inflating the layout
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        // Set the content view to the root of the binding
        setContentView(binding.getRoot());

        // Access views via the binding object instead of findViewById
        binding.bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.nav_home){
                    loadFragment(new HomeFragment(), false);
                } else if (itemId == R.id.nav_scan) {
                    loadFragment(new ScanFragment(), false);
                } else {
                    loadFragment(new LocationFragment(), false);
                }

                return true;
            }
        });

        // Load the home fragment initially
        loadFragment(new HomeFragment(), true);
    }

    private void loadFragment(Fragment fragment, Boolean isAppInit) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (isAppInit) {
            fragmentTransaction.add(binding.frameLayout.getId(), fragment);
        } else {
            fragmentTransaction.replace(binding.frameLayout.getId(), fragment);
        }
        fragmentTransaction.commit();
    }
}

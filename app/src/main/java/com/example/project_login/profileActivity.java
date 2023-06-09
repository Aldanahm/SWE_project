package com.example.project_login;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.project_login.databinding.ActivityProfileBinding;


public class profileActivity extends AppCompatActivity {
    ActivityProfileBinding binding;
    Button logout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());
        setContentView(R.layout.activity_profile);
        logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profileActivity.this,MainActivity.class);
                Intent intentL = null;
                startActivity(intentL);
                finish();
                Toast.makeText(profileActivity.this, "Succesfully logout!", Toast.LENGTH_SHORT).show();

            }
        });

        binding.bottomNavigationView2.setOnItemSelectedListener(this::onNavigationItemSelected


        );
    }
    private void replaceFragment(Fragment fragment ){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

    private boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.home:
                replaceFragment(new HomeFragment());
                break;


            case R.id.profile:
                replaceFragment(new com.example.project_login.ProfileFragment());
                break;


            case R.id.cart:
                replaceFragment(new CartFragment());
                break;

        }

        return true;


    }
}
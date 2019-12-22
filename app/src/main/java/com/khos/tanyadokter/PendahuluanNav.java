package com.khos.tanyadokter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.khos.tanyadokter.fragment.Fragment_welcome;

public class PendahuluanNav extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendahuluan_nav);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.n_pendahuluan:
//                Intent c = new Intent(PendahuluanNav.this, PendahuluanNav.class);
//                startActivity(c);
                        break;
                    case R.id.n_list:
                        Intent a = new Intent(PendahuluanNav.this, Welcome.class);
                        startActivity(a);
                        break;
                    case R.id.n_tanya:
                        Intent b = new Intent(PendahuluanNav.this, MainActivity.class);
                        startActivity(b);
                        break;
                }
                return false;
            }
        });
    }

    public void loadFragmentWelcome(View view) {
        Intent intent = new Intent(this, Pendahuluan.class);
        startActivity(intent);
    }
}

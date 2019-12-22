package com.khos.tanyadokter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.khos.tanyadokter.fragment.Fragment_welcome;

public class Pendahuluan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendahuluan);
    }

    public void handlerClickLoadList(View view) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.replace(R.id.dynamic_fragment_placeholder,new Fragment_welcome());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void handlerClickLoadFragment(View view) {
        Intent intent = new Intent(this, Pendahuluan.class);
        startActivity(intent);
    }

    public void handlerClickHome(View view) {
        Intent intent = new Intent(this, PendahuluanNav.class);
        startActivity(intent);
    }
}

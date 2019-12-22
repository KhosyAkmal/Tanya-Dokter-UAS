package com.khos.tanyadokter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.khos.tanyadokter.fragment.Fragment_welcome;
import com.khos.tanyadokter.model.keluhan;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "data";
    private DatabaseReference database;

    private EditText nama, nohp, kel;
    Button button;
    private ProgressDialog loading;

    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance().getReference();

        nama = findViewById(R.id.f_nama);
        nohp = findViewById(R.id.f_nohp);
        kel = findViewById(R.id.f_keluhan);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.n_pendahuluan:
                        Intent c = new Intent(MainActivity.this, PendahuluanNav.class);
                        startActivity(c);
                        break;
                    case R.id.n_list:
                        Intent a = new Intent(MainActivity.this, Welcome.class);
                        startActivity(a);
                        break;
                    case R.id.n_tanya:
                        Intent b = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(b);
                        break;
                }
                return false;
            }
        });
    }

    private void SubmitData (keluhan keluhan){
        database.child("Data")
                .push()
                .setValue(keluhan)
                .addOnSuccessListener(this, new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object aVoid) {
                        nama.setText("");
                        nohp.setText("");
                        kel.setText("");

                        Toast.makeText(MainActivity.this,
                                "Data Berhasil Ditambahkan",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void handlerClikDaftarkan(View view) {
        String _nama = nama.getText().toString();
        String _nohp = nohp.getText().toString();
        String _kel = kel.getText().toString();

        if (_nama.equals("")) {
            nama.setError("Silahkan Masukan Nama");
            nama.requestFocus();
        }  else if (_nohp.equals("")) {
            nohp.setError("Silahkan Masukan Jumlah Barang");
            nohp.requestFocus();
        } else if (_kel.equals("")) {
            kel.setError("Silahkan Masukan Harga Barang");
            kel.requestFocus();
        } else {

            SubmitData(new keluhan(
                    _nama.toLowerCase(),
                    _nohp.toLowerCase(),
                    _kel.toLowerCase()
            ));
        }
    }
}


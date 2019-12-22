package com.khos.tanyadokter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.khos.tanyadokter.adapter.KeluhanAdapter;
import com.khos.tanyadokter.fragment.Fragment_welcome;
import com.khos.tanyadokter.model.keluhan;

import java.util.ArrayList;

public class Welcome extends AppCompatActivity {

    private DatabaseReference database;

    private ArrayList<keluhan> daftarReq;
    private KeluhanAdapter keluhanAdapter;

    private RecyclerView list_item;
    private ProgressDialog loading;
//    private FloatingActionButton fab_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.n_pendahuluan:
                        Intent c = new Intent(Welcome.this, PendahuluanNav.class);
                        startActivity(c);
                        break;
                    case R.id.n_list:
//                        Intent a = new Intent(Welcome.this, Welcome.class);
//                        startActivity(a);
                        break;
                    case R.id.n_tanya:
                        Intent b = new Intent(Welcome.this, MainActivity.class);
                        startActivity(b);
                        break;
                }
                return false;
            }
        });

        database = FirebaseDatabase.getInstance().getReference();

        list_item = findViewById(R.id.list_item);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        list_item.setLayoutManager(mLayoutManager);
        list_item.setItemAnimator(new DefaultItemAnimator());

        loading = ProgressDialog.show(Welcome.this,
                null,
                "Please wait...",
                true,
                false);

        database.child("Data").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                /**
                 * Saat ada data baru, masukkan datanya ke ArrayList
                 */
                daftarReq = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    /**
                     * Mapping data pada DataSnapshot ke dalam object Wisata
                     * Dan juga menyimpan primary key pada object Wisata
                     * untuk keperluan Edit dan Delete data
                     */
                    keluhan requests = noteDataSnapshot.getValue(keluhan.class);
                    requests.setKey(noteDataSnapshot.getKey());

                    /**
                     * Menambahkan object Wisata yang sudah dimapping
                     * ke dalam ArrayList
                     */
                    daftarReq.add(requests);
                }

                /**
                 * Inisialisasi adapter dan data hotel dalam bentuk ArrayList
                 * dan mengeset Adapter ke dalam RecyclerView
                 */
                keluhanAdapter = new KeluhanAdapter(daftarReq, Welcome.this);
                list_item.setAdapter(keluhanAdapter);
                loading.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                /**
                 * Kode ini akan dipanggil ketika ada error dan
                 * pengambilan data gagal dan memprint error nya
                 * ke LogCat
                 */
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
                loading.dismiss();
            }
        });
    }
}

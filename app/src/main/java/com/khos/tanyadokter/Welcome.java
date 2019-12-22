package com.khos.tanyadokter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

        database = FirebaseDatabase.getInstance().getReference();

        list_item = findViewById(R.id.list_item);
//        fab_add = findViewById(R.id.fab_add);

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

    public void loadFragmentWelcome(View view) {
        Intent intent = new Intent(this, Pendahuluan.class);
        startActivity(intent);
    }

    public void loadActivityList(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}

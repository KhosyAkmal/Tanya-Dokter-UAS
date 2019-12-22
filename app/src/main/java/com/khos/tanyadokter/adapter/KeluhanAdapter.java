package com.khos.tanyadokter.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.khos.tanyadokter.R;
import com.khos.tanyadokter.Welcome;
import com.khos.tanyadokter.model.keluhan;

import java.util.List;

public class KeluhanAdapter extends RecyclerView.Adapter<KeluhanAdapter.MyViewHolder> {

    private List<keluhan> listKeluhan;
    private Activity mActivity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout rl_layout;
        public TextView v_nama, v_nohp, v_kel;

        public MyViewHolder(View view) {
            super(view);
            rl_layout = view.findViewById(R.id.rl_layout);
            v_nama = view.findViewById(R.id.v_nama);
            v_nohp = view.findViewById(R.id.v_nohp);
            v_kel = view.findViewById(R.id.v_kel);
        }
    }

    public KeluhanAdapter(List<keluhan> listKeluhan, Activity activity) {
        this.listKeluhan = listKeluhan;
        this.mActivity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_kel, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final keluhan k = listKeluhan.get(position);

        holder.v_nama.setText(k.getNama());
        holder.v_nohp.setText(k.getNohp());
        holder.v_kel.setText(k.getKel());

        holder.rl_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goDetail = new Intent(mActivity, Welcome.class);
                goDetail.putExtra("id", k.getKey());
                goDetail.putExtra("nama", k.getNama());
                goDetail.putExtra("nohp", k.getNohp());
                goDetail.putExtra("kel", k.getKel());

                mActivity.startActivity(goDetail);


            }
        });

    }

    @Override
    public int getItemCount() {
        return listKeluhan.size();
    }


}

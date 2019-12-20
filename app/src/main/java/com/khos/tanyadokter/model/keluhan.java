package com.khos.tanyadokter.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class keluhan implements Serializable {

    private String nama;
    private String nohp;
    private String kel;

    private String key;

    public keluhan() {
    }

    public keluhan(String nama, String nohp, String kel) {
        this.nama = nama;
        this.nohp = nohp;
        this.kel = kel;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public void setKel(String kel) {
        this.kel = kel;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNama() {
        return nama;
    }

    public String getNohp() {
        return nohp;
    }

    public String getKel() {
        return kel;
    }

    public String getKey() {
        return key;
    }

    @NonNull
    @Override
    public String toString() {
        return " "+nama+"\n"+ " "+nohp+"\n"+ " "+kel;
    }
}

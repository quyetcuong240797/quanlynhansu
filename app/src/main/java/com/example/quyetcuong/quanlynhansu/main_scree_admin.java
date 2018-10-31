package com.example.quyetcuong.quanlynhansu;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.quyetcuong.quanlynhansu.data.DBManager;

public class main_scree_admin extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_admin_screen);

        DBManager dbManager = new DBManager(this);
    }
}

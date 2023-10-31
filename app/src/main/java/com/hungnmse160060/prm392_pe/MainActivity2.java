package com.hungnmse160060.prm392_pe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.hungnmse160060.prm392_pe.R;
import com.hungnmse160060.prm392_pe.model.BoMon;
import com.hungnmse160060.prm392_pe.repository.BoMonRepository;
import com.hungnmse160060.prm392_pe.service.BoMonService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {
    EditText txtNameBoMon;
    Button btnAddBoMon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txtNameBoMon = findViewById(R.id.etNameBoMon);
        btnAddBoMon = findViewById(R.id.btnAddBoMon);
        btnAddBoMon.setOnClickListener(view -> {
            if (txtNameBoMon.getText().toString().trim().length() == 0) {
                txtNameBoMon.setError("Name bo mon should not be empty");
            }
            else {
                BoMonService boMonService = BoMonRepository.getBoMonService();
                BoMon boMon = new BoMon(txtNameBoMon.getText().toString(), null);
                Call<BoMon> call = boMonService.create(boMon);
                call.enqueue(new Callback<BoMon>() {
                    @Override
                    public void onResponse(Call<BoMon> call, Response<BoMon> response) {
                        startActivity(new Intent(MainActivity2.this, MainActivity.class));
                    }

                    @Override
                    public void onFailure(Call<BoMon> call, Throwable t) {

                    }
                });
            }
        });
    }
}
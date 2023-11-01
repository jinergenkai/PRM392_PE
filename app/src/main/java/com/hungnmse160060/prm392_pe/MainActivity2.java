package com.hungnmse160060.prm392_pe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.hungnmse160060.prm392_pe.R;
import com.hungnmse160060.prm392_pe.model.Nganh;
import com.hungnmse160060.prm392_pe.repository.NganhRepository;
import com.hungnmse160060.prm392_pe.service.NganhService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {
    EditText txtNameNganh;
    Button btnAddNganh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txtNameNganh = findViewById(R.id.etNameNganh);
        btnAddNganh = findViewById(R.id.btnAddNganh);
        btnAddNganh.setOnClickListener(view -> {
            if (txtNameNganh.getText().toString().trim().length() == 0) {
                txtNameNganh.setError("Name bo mon should not be empty");
            }
            else {
                NganhService nganhService = NganhRepository.getNganhService();
                Nganh nganh = new Nganh(txtNameNganh.getText().toString(), null);
                Call<Nganh> call = nganhService.create(nganh);
                call.enqueue(new Callback<Nganh>() {
                    @Override
                    public void onResponse(Call<Nganh> call, Response<Nganh> response) {
                        startActivity(new Intent(MainActivity2.this, MainActivity.class));
                    }

                    @Override
                    public void onFailure(Call<Nganh> call, Throwable t) {

                    }
                });
            }
        });
    }
}
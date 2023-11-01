package com.hungnmse160060.prm392_pe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.hungnmse160060.prm392_pe.R;
import com.hungnmse160060.prm392_pe.adapter.Adapter;
import com.hungnmse160060.prm392_pe.repository.SinhVienRepository;
import com.hungnmse160060.prm392_pe.model.Nganh;
import com.hungnmse160060.prm392_pe.model.SinhVien;
import com.hungnmse160060.prm392_pe.repository.NganhRepository;
import com.hungnmse160060.prm392_pe.service.NganhService;
import com.hungnmse160060.prm392_pe.service.SinhVienService;

public class MainActivity extends AppCompatActivity {

    private ListView lvItem;
    private Adapter adapter;
    private Spinner spinner;
    private List<SinhVien> items;
    private List<Nganh> aItems;
    private EditText etName;
    private EditText etDate;
    private EditText etGender;
    private EditText etAddress;
    private Button btnAdd;
    private Button btnUpdate;
    private Button btnDelete;
    private Button btnNewNganh;

    private Button btnMap;
    private int curPos;
    private int curAPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvItem = findViewById(R.id.lvItem);
        spinner = findViewById(R.id.spA);
        etName = findViewById(R.id.etName);
        etDate = findViewById(R.id.etDate);
        etGender = findViewById(R.id.etGender);
        etAddress = findViewById(R.id.etAddress);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnNewNganh = findViewById(R.id.btnNewBomon);

        btnMap = findViewById(R.id.btnMap);
        btnMap.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, GoogleMapActivity.class);
            intent.putExtra("Address", etAddress.getText().toString());
            startActivity(intent);
        });

        btnNewNganh.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, MainActivity2.class));
        });
        loadData();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                curAPos = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        lvItem.setOnItemClickListener((adapterView, view, i, l) -> {
            curPos = i;
            etName.setText(items.get(i).getName());
            etDate.setText(items.get(i).getDate());
            etGender.setText(items.get(i).getGender());
            etAddress.setText(items.get(i).getAddress());
            //spinner.setSelection(2);
            for (int j = 0; j < aItems.size(); ++j) {
                //System.out.println(aItems.get(j).getId());
                //System.out.println(items.get(i).getAId());
                if (aItems.get(j).getId() == items.get(i).getIdBomon()) {
                    spinner.setSelection(j);
                    curAPos = j;
                    break;
                }
            }
        });
        btnAdd.setOnClickListener(view -> {
            if (!validateForAdd()) return;
            Long aId = aItems.get(curAPos).getId();
            SinhVien b = new SinhVien(null, etName.getText().toString(), etDate.getText().toString(), etGender.getText().toString(), etAddress.getText().toString(), aId);
            SinhVienService bService = SinhVienRepository.getSinhVienService();
            Call<SinhVien> call = bService.create(b);
            call.enqueue(new Callback<SinhVien>() {
                @Override
                public void onResponse(Call<SinhVien> call, Response<SinhVien> response) {
                    loadData();
                    reset();
                }

                @Override
                public void onFailure(Call<SinhVien> call, Throwable t) {

                }
            });
        });
        btnUpdate.setOnClickListener(view -> {
            if (!validate()) return;
            Long aId = aItems.get(curAPos).getId();
            SinhVien b = items.get(curPos);
            b.setName(etName.getText().toString());
            b.setDate(etDate.getText().toString());
            b.setGender(etGender.getText().toString());
            b.setAddress(etAddress.getText().toString());
            b.setIdBomon(aId);
            SinhVienService bService = SinhVienRepository.getSinhVienService();
            Call<SinhVien> call = bService.update(b.getId(), b);
            call.enqueue(new Callback<SinhVien>() {
                @Override
                public void onResponse(Call<SinhVien> call, Response<SinhVien> response) {
                    loadData();
                    reset();
                }

                @Override
                public void onFailure(Call<SinhVien> call, Throwable t) {

                }
            });
        });
        btnDelete.setOnClickListener(view -> {
            if (!validate()) return;
            SinhVien b = items.get(curPos);
            SinhVienService bService = SinhVienRepository.getSinhVienService();
            Call<SinhVien> call = bService.delete(b.getId());
            call.enqueue(new Callback<SinhVien>() {
                @Override
                public void onResponse(Call<SinhVien> call, Response<SinhVien> response) {
                    loadData();
                    reset();
                }

                @Override
                public void onFailure(Call<SinhVien> call, Throwable t) {

                }
            });
        });
    }

    private boolean validate() {
        if (curPos == -1) {
            return false;
        }
        if (etName.getText().toString().trim().length() == 0) {
            etName.setError("Name can not be empty");
            return false;
        }
        if (etGender.getText().toString().trim().length() == 0) {
            etGender.setError("Gender can not be empty");
            return false;
        }
        if (etDate.getText().toString().trim().length() == 0) {
            etDate.setError("Date can not be empty");
            return false;
        }
        if (etAddress.getText().toString().trim().length() == 0) {
            etAddress.setError("Address can not be empty");
            return false;
        }
        try {
            new SimpleDateFormat("dd/MM/yyyy").parse(etDate.getText().toString().trim());
        }catch (Exception e) {
            etDate.setError("Date should be in format dd/MM/yyyy");
            return false;
        }
        return true;
    }

    private boolean validateForAdd() {
        if (etName.getText().toString().trim().length() == 0) {
            etName.setError("Name can not be empty");
            return false;
        }
        if (etGender.getText().toString().trim().length() == 0) {
            etGender.setError("Gender can not be empty");
            return false;
        }
        if (etDate.getText().toString().trim().length() == 0) {
            etDate.setError("Date can not be empty");
            return false;
        }
        if (etAddress.getText().toString().trim().length() == 0) {
            etAddress.setError("Address can not be empty");
            return false;
        }
        try {
            new SimpleDateFormat("dd/MM/yyyy").parse(etDate.getText().toString().trim());
        }catch (Exception e) {
            etDate.setError("Date should be in format dd/MM/yyyy");
            return false;
        }
        return true;
    }

    private void reset() {
        curPos = -1;
        curAPos = 0;
        etName.setText("");
        etDate.setText("");
        etGender.setText("");
        etAddress.setText("");
        spinner.setSelection(0);
    }

    private void loadData() {
        SinhVienService bService = SinhVienRepository.getSinhVienService();
        Call<SinhVien[]> call = bService.getAll();
        call.enqueue(new Callback<SinhVien[]>() {
            @Override
            public void onResponse(Call<SinhVien[]> call, Response<SinhVien[]> response) {
                System.out.println(response.code());
                SinhVien[] data = response.body();
                items = new ArrayList<>();
                for (int i = 0; i < data.length; ++i) {
                    items.add(data[i]);
                }
                adapter = new Adapter(MainActivity.this, items, R.layout.item_row);
                lvItem.setAdapter(adapter);
                curPos = -1;
            }

            @Override
            public void onFailure(Call<SinhVien[]> call, Throwable t) {

            }
        });
        NganhService aService = NganhRepository.getNganhService();
        Call<Nganh[]> call1 = aService.getAll();
        call1.enqueue(new Callback<Nganh[]>() {
            @Override
            public void onResponse(Call<Nganh[]> call, Response<Nganh[]> response) {
                System.out.println(response.code());
                Nganh[] data = response.body();
                aItems = new ArrayList<>();
                List<String> cc = new ArrayList<>();
                for (int i = 0; i < data.length; ++i) {
                    aItems.add(data[i]);
                    cc.add(data[i].getNameBM());
                }
                ArrayAdapter<String> spAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, cc);
                spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(spAdapter);
                curAPos = 0;
            }

            @Override
            public void onFailure(Call<Nganh[]> call, Throwable t) {

            }
        });
    }
}
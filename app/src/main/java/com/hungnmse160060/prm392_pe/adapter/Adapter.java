package com.hungnmse160060.prm392_pe.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hungnmse160060.prm392_pe.MainActivity;
import com.hungnmse160060.prm392_pe.R;
import com.hungnmse160060.prm392_pe.model.BoMon;
import com.hungnmse160060.prm392_pe.model.SinhVien;
import com.hungnmse160060.prm392_pe.repository.BoMonRepository;
import com.hungnmse160060.prm392_pe.service.BoMonService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class Adapter extends BaseAdapter {
    private MainActivity context;
    private List<SinhVien> items;
    private int layout;

    public Adapter(MainActivity context,List<SinhVien>  items, int layout) {
        this.context = context;
        this.items = items;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        TextView txtItemBoMon;
        TextView txtItemName;
        TextView txtGender;
        TextView txtDate;
        TextView txtAddress;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txtItemBoMon = view.findViewById(R.id.txtItemBoMon);
            holder.txtItemName = view.findViewById(R.id.txtItemName);
            holder.txtDate = view.findViewById(R.id.txtItemDate);
            holder.txtGender = view.findViewById(R.id.txtItemGender);
            holder.txtAddress = view.findViewById(R.id.txtItemAddress);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }
        SinhVien item = items.get(i);
        holder.txtItemName.setText("Name: " + item.getName());
        holder.txtGender.setText("Gender: " + item.getGender());
        holder.txtDate.setText("Date: " + item.getDate());
        holder.txtAddress.setText("Address: " + item.getAddress());
        BoMonService aService = BoMonRepository.getBoMonService();
        Call<BoMon> call = aService.getById(item.getIdBomon());
        call.enqueue(new Callback<BoMon>() {
            @Override
            public void onResponse(Call<BoMon> call, Response<BoMon> response) {
                System.out.println(response.code() + response.message());
                BoMon a = response.body();
                holder.txtItemBoMon.setText("Bo Mon: " + a.getNameBM());
            }

            @Override
            public void onFailure(Call<BoMon> call, Throwable t) {

            }
        });
        return view;
    }
}

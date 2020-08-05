package com.example.didong.Apdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.didong.GiaoDien.ChiTietNVActivity;
import com.example.didong.Model.NhanVien;
import com.example.didong.R;

import java.util.ArrayList;
import java.util.Locale;

public class CustomAdapterNV extends ArrayAdapter  {
    Context context;
    int resource;
    ArrayList<NhanVien> data;
    ArrayList<NhanVien> data_DS;

    public CustomAdapterNV(Context context, int resource, ArrayList<NhanVien> data) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.data = data;
        this.data_DS = new ArrayList<NhanVien>();
        this.data_DS.addAll(data);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    private static class Holder {
        ImageView imgDetail;
        ImageView imgHinh;
        TextView tvTen;
        TextView tvNgay;
        TextView tvPB;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Holder holder = null;
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource, null);
            holder.imgDetail = view.findViewById(R.id.imgDetailNV);
            holder.imgHinh = view.findViewById(R.id.imgHinhNV);
            holder.tvTen = view.findViewById(R.id.tvTenNV);
            holder.tvNgay = view.findViewById(R.id.tvNgay);
            holder.tvPB = view.findViewById(R.id.tvPB_NV);

            view.setTag(holder);
        } else
            holder = (Holder) view.getTag();

        final NhanVien nhanVien = data.get(position);

        holder.imgHinh.setBackgroundResource(R.drawable.nv);
        holder.tvTen.setText(nhanVien.getTen());
        holder.tvNgay.setText(nhanVien.getNgaySinh());
        holder.tvPB.setText(nhanVien.getPhongBan());

        holder.imgDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i( "TAG", "onClick: vao roi" );
                Intent intent = new Intent( context, ChiTietNVActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("MaNV", nhanVien.getMa());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return view;
    }

    //filter
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        data.clear();
        if (charText.length() == 0) {
            data.addAll(data_DS);
        } else {
            for (NhanVien model : data_DS) {
                if (model.getTen().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    data.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }
}

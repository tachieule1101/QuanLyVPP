package com.example.didong.Apdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.didong.GiaoDien.ChiTietPBActivity;
import com.example.didong.Model.PhongBan;
import com.example.didong.R;

import java.util.ArrayList;
import java.util.Locale;

public class CustomAdapterPB extends ArrayAdapter  {
    Context context;
    int resource;
    ArrayList<PhongBan> data;
    ArrayList<PhongBan> data_DS;

    public CustomAdapterPB(Context context, int resource, ArrayList<PhongBan> data) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.data = data;
        this.data_DS = new ArrayList<PhongBan>();
        this.data_DS.addAll(data);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    private static class Holder {
        ImageView imgHinh;
        ImageView imgDetail;
        TextView tvTen;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Holder holder = null;
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource, null);
            holder.imgHinh = view.findViewById(R.id.imgHinhPB);
            holder.tvTen = view.findViewById(R.id.tvTenPB);
            holder.imgDetail = view.findViewById(R.id.imgDetailPB);
            view.setTag(holder);
        } else
            holder = (Holder) view.getTag();

        final PhongBan phongBan = data.get(position);

        if (phongBan.getMa().equals("PB01"))
        {
            holder.imgHinh.setBackgroundResource(R.drawable.phong_ky_thuat);
        }
        else if (phongBan.getMa().equals("PB02"))
        {
            holder.imgHinh.setBackgroundResource(R.drawable.phong_tai_chinh);
        }
        else if (phongBan.getMa().equals("PB03"))
        {
            holder.imgHinh.setBackgroundResource(R.drawable.phong_thiet_ke);
        }
        else
        {
            holder.imgHinh.setBackgroundResource(R.drawable.hinh1);
        }

        holder.tvTen.setText(phongBan.getTen());

        holder.imgDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( context, ChiTietPBActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("MaPB", phongBan.getMa());
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
            for (PhongBan model : data_DS) {
                if (model.getTen().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    data.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }
}

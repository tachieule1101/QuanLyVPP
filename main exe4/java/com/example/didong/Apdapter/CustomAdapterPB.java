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

import com.example.didong.GiaoDien.ChiTietActivity;
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
        ImageView imgDetail;
        TextView tvTen;
        TextView tvMa;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Holder holder = null;
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource, null);
            holder.imgDetail = view.findViewById(R.id.imgDetail);
            holder.tvMa = view.findViewById(R.id.tvMaPB);
            holder.tvTen = view.findViewById(R.id.tvTenPB);

            view.setTag(holder);
        } else
            holder = (Holder) view.getTag();

        final PhongBan phongBan = data.get(position);

        holder.tvMa.setText(phongBan.getMa());
        holder.tvTen.setText(phongBan.getTen());

        holder.imgDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i( "TAG", "onClick: vao roi" );
                Intent intent = new Intent( context, ChiTietActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("ma", phongBan.getMa());
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

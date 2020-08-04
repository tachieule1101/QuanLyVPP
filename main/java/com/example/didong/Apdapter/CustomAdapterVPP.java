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

import com.example.didong.GiaoDien.ChiTietVPPActivity;
import com.example.didong.Model.VPP;
import com.example.didong.R;

import java.util.ArrayList;
import java.util.Locale;

public class CustomAdapterVPP extends ArrayAdapter  {
    Context context;
    int resource;
    ArrayList<VPP> data;
    ArrayList<VPP> data_DS;

    public CustomAdapterVPP(Context context, int resource, ArrayList<VPP> data) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.data = data;
        this.data_DS = new ArrayList<VPP>();
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
        TextView tvDVT;
        TextView tvGia;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Holder holder = null;
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource, null);
            holder.imgDetail = view.findViewById(R.id.imgDetail);
            holder.tvMa = view.findViewById(R.id.tvMaVPP);
            holder.tvTen = view.findViewById(R.id.tvTenVPP);
            holder.tvDVT = view.findViewById(R.id.tvDVT);
            holder.tvGia = view.findViewById(R.id.tvGia);

            view.setTag(holder);
        } else
            holder = (Holder) view.getTag();

        final VPP vpp = data.get(position);

        holder.tvMa.setText(vpp.getMa());
        holder.tvTen.setText(vpp.getTen());
        holder.tvDVT.setText(vpp.getDvt());
        holder.tvGia.setText(vpp.getGia());

        holder.imgDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i( "TAG", "onClick: vao roi" );
                Intent intent = new Intent( context, ChiTietVPPActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("ma", vpp.getMa());
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
            for (VPP model : data_DS) {
                if (model.getTen().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    data.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }
}

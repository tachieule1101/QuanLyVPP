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

import com.example.didong.GiaoDien.ChiTietCPActivity;
import com.example.didong.GiaoDien.ChiTietVPPActivity;
import com.example.didong.Model.CapPhat;
import com.example.didong.Model.VPP;
import com.example.didong.R;

import java.util.ArrayList;
import java.util.Locale;

public class CustomAdapterCP extends ArrayAdapter  {
    Context context;
    int resource;
    ArrayList<CapPhat> data;
    ArrayList<CapPhat> data_DS;

    public CustomAdapterCP(Context context, int resource, ArrayList<CapPhat> data) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.data = data;
        this.data_DS = new ArrayList<CapPhat>();
        this.data_DS.addAll(data);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    private static class Holder {
        ImageView imgDetail;
        TextView tvNgay;
        ImageView imgHinh;
        TextView tvVPP;
        TextView tvNV;
        TextView tvSL;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Holder holder = null;
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource, null);
            holder.imgDetail = view.findViewById(R.id.imgDetailCP);
            holder.imgHinh = view.findViewById(R.id.imgHinhCP);

            holder.tvNgay = view.findViewById(R.id.tvNgayCP);
            holder.tvVPP = view.findViewById(R.id.tvVPP);
            holder.tvNV = view.findViewById(R.id.tvNV);
            holder.tvSL = view.findViewById(R.id.tvSL);

            view.setTag(holder);
        } else
            holder = (Holder) view.getTag();

        final CapPhat capPhat = data.get(position);

        holder.imgHinh.setBackgroundResource(R.drawable.hinh1);

        holder.tvNgay.setText(capPhat.getNgay());
        holder.tvVPP.setText(capPhat.getVpp());
        holder.tvNV.setText(capPhat.getNv());


        holder.tvSL.setText(capPhat.getSl());

        holder.imgDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( context, ChiTietCPActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("MaCP", capPhat.getMa());
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
            for (CapPhat model : data_DS) {
                if (model.getMa().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    data.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }
}

package tdc.edu.vn.myapplication.Adapter;

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



import java.util.ArrayList;
import java.util.Locale;

import tdc.edu.vn.myapplication.Model.PhongBan;

public class CustomApdapterPB extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<PhongBan> data;
    ArrayList<PhongBan> data_DS;

    public CustomApdapterPB(Context context, int resource, ArrayList<PhongBan> data) {
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
        TextView tvMa;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Holder holder = null;
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource, null);
            //holder.imgHinh = view.findViewById( R.id.imgHinh);
            //holder.imgDetail = view.findViewById(R.id.imgDetail);
            //holder.tvTen = view.findViewById(R.id.tvten);
            //holder.tvMa = view.findViewById(R.id.tvMa);

            view.setTag(holder);
        } else
            holder = (Holder) view.getTag();

        final PhongBan PhongBan = data.get(position);

        holder.tvTen.setText(PhongBan.getTen());
        holder.tvMa.setText(PhongBan.getMa());

        holder.imgDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i( "TAG", "onClick: vao roi" );
                //Intent intent = new Intent( context, ChiTietActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("ma", PhongBan.getMa());
                //intent.putExtras(bundle);
                //context.startActivity(intent);


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

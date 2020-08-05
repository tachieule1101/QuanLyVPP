package com.example.didong.Apdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.didong.GiaoDien.Contact;
import com.example.didong.R;

import java.util.ArrayList;

public class CustomAdapterContact extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Contact> data;
    ArrayList<Contact> data_DS;

    public CustomAdapterContact(@NonNull Context context, int resource, ArrayList<Contact> data){
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.data = data;
        this.data_DS = new ArrayList<Contact>();
        this.data_DS.addAll(data);
    }
    @Override
    public int getCount(){return data.size();}
    private static class Holder{
        TextView textView;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        Holder holder = null;
        if (view == null){
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource, null);
            holder.textView = view.findViewById(R.id.textView);

            view.setTag(holder);
        }else
            holder =(Holder) view.getTag();

            final Contact contact = data.get(position);
            holder.textView.setText(contact.getTitle());

            return view;

    }
}

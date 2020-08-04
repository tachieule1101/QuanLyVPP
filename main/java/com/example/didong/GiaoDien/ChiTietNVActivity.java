package com.example.didong.GiaoDien;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.didong.Database.DBNhanVien;
import com.example.didong.Model.NhanVien;
import com.example.didong.R;

import java.util.ArrayList;

public class ChiTietNVActivity extends AppCompatActivity {

    Spinner spnPB;
    EditText txtMaNV, txtTenNV, txtNgay, txtPB;
    ArrayList<NhanVien> data_NV = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_chi_tiet_nv);
        setConTrol();
        setEvent();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    private void setEvent() {
        String ma= getIntent().getExtras().getString("ma");

        DBNhanVien dbNhanVien = new DBNhanVien(this);
        data_NV = dbNhanVien.LayDL(ma);
        txtMaNV.setText(data_NV.get(0).getMa());
        txtTenNV.setText(data_NV.get(0).getTen());
        txtNgay.setText(data_NV.get(0).getNgaySinh());
        txtPB.setText(data_NV.get(0).getPhongBan());
        /*spnPB.getItemAtPosition(0);*/
    }

    private void setConTrol() {
        txtMaNV = findViewById(R.id.txtMaNV);
        txtTenNV = findViewById(R.id.txtTenNV);
        txtNgay = findViewById(R.id.txtNgaySinh);
        txtPB = findViewById(R.id.txtPB_NV);
        /*spnPB = findViewById(R.id.spnPB);*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

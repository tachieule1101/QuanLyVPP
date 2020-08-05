package com.example.didong.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.didong.Database.DBNhanVien;
import com.example.didong.Database.DBPhongBan;
import com.example.didong.Model.NhanVien;
import com.example.didong.Model.PhongBan;
import com.example.didong.R;

import java.util.ArrayList;

public class ChiTietNVActivity extends AppCompatActivity {

    TextView tvMa;
    EditText txtTen, txtNgay;
    Spinner spnPB;
    Button btnXoa, btnSua;
    ArrayList<NhanVien> data_NV = new ArrayList<>();
    ArrayList<PhongBan> data_PB = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_nv);
        setConTrol();
        setEvent();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setEvent() {
        String ma= getIntent().getExtras().getString("MaNV");

        DBNhanVien dbNhanVien = new DBNhanVien(this);
        data_NV = dbNhanVien.LayDL(ma);

        tvMa.setText(data_NV.get(0).getMa());
        txtTen.setText(data_NV.get(0).getTen());
        txtNgay.setText(data_NV.get(0).getNgaySinh());

        DBPhongBan dbPhongBan = new DBPhongBan(this);
        data_PB = dbPhongBan.LayDL();

        ArrayList<String> l = new ArrayList<>();
        for (int i = 0; i < data_PB.size(); i++) {
            l.add(data_PB.get(i).getTen());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,l);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        spnPB.setAdapter(adapter);

        for (int i = 0; i < data_PB.size(); i++) {
            if (data_PB.get(i).getMa().equals(data_NV.get(0).getPhongBan()))
            {
                spnPB.setSelection(i);
                break;
            }
        }

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ChiTietNVActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn muốn xóa?");

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        XoaDL();
                        Intent intent = new Intent( ChiTietNVActivity.this, NVActivity.class);
                        startActivity( intent );
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ChiTietNVActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn muốn sửa?");

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SuaDL();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private void setConTrol() {
        tvMa = findViewById(R.id.tvMaNV);
        txtTen = findViewById(R.id.txtCTTenNV);
        txtNgay = findViewById(R.id.txtNgaySinh);
        spnPB = findViewById(R.id.spnPB);

        btnSua = findViewById(R.id.btnSua);
        btnXoa = findViewById(R.id.btnXoa);
    }

    private void XoaDL() {
        DBNhanVien dbNhanVien = new DBNhanVien(this);
        NhanVien nhanVien = new NhanVien();

        nhanVien.setMa(tvMa.getText().toString());
        nhanVien.setTen(txtTen.getText().toString());
        nhanVien.setTen(txtNgay.getText().toString());
        nhanVien.setTen(spnPB.getSelectedItem().toString());
        dbNhanVien.Xoa(nhanVien);
    }

    private void SuaDL() {
        DBNhanVien dbNhanVien = new DBNhanVien(this);
        NhanVien nhanVien = new NhanVien();

        nhanVien.setMa(tvMa.getText().toString());
        nhanVien.setTen(txtTen.getText().toString());
        nhanVien.setTen(txtNgay.getText().toString());
        nhanVien.setTen(spnPB.getSelectedItem().toString());
        dbNhanVien.Sua(nhanVien);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.mnExit) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ChiTietNVActivity.this);
            builder.setTitle("Thông báo");
            builder.setMessage("Bạn muốn thoát?");

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent( ChiTietNVActivity.this, NVActivity.class);
                    startActivity( intent );
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }

        return super.onOptionsItemSelected(item);
    }
}
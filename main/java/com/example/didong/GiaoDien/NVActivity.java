package com.example.didong.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.didong.Apdapter.CustomAdapterNV;
import com.example.didong.Database.DBNhanVien;
import com.example.didong.Database.DBPhongBan;
import com.example.didong.Model.NhanVien;
import com.example.didong.Model.PhongBan;
import com.example.didong.R;

import java.util.ArrayList;

public class NVActivity extends AppCompatActivity{

    Spinner spin;
    Button btnThem, btnXoa, btnSua;
    EditText txtMaNV, txtTenNV, txtNgay;
    ListView lvNV;
    boolean ngonngu = true;

    CustomAdapterNV apdapter;
    ArrayList<NhanVien> data_NV = new ArrayList<>();
    ArrayList<PhongBan> data_PB = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_v);

        setConTrol();
        setEvent();
    }

    private void setEvent() {

        DBPhongBan dbPhongBan = new DBPhongBan(this);

        data_PB = dbPhongBan.LayDL();
        ArrayList<String> l = new ArrayList<>();
        for (int i = 0; i < data_PB.size(); i++) {
            l.add(data_PB.get(i).getTen());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,l);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        spin.setAdapter(adapter);

        HienThiDL();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ThemDL();
                HienThiDL();

            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(NVActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn muốn xóa?");

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        XoaDL();
                        HienThiDL();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(NVActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn muốn sửa?");

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SuaDL();
                        HienThiDL();
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

        lvNV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NhanVien nhanVien = data_NV.get(position);
                txtMaNV.setText(nhanVien.getMa());
                txtTenNV.setText(nhanVien.getTen());
                txtNgay.setText(nhanVien.getNgaySinh());

                for (int i = 0; i < data_PB.size(); i++) {
                    if (data_PB.get(i).getMa().equals(nhanVien.getPhongBan()))
                    {
                        spin.setSelection(i);
                        break;
                    }
                }
            }
        });

    }

    private void HienThiDL() {
        DBNhanVien dbNhanVien = new DBNhanVien(this);

        data_NV = dbNhanVien.LayDL();
        apdapter = new CustomAdapterNV(this, R.layout.activity_lv_nv, data_NV);
        lvNV.setAdapter(apdapter);
    }

    private void ThemDL() {
        DBNhanVien dbNhanVien = new DBNhanVien(this);
        NhanVien nhanVien = new NhanVien();

        nhanVien.setMa(txtMaNV.getText().toString());
        nhanVien.setTen(txtTenNV.getText().toString());
        nhanVien.setNgaySinh(txtNgay.getText().toString());

        for (int i = 0; i < data_PB.size(); i++) {
            if(spin.getSelectedItem().toString() == data_PB.get(i).getTen())
            {
                nhanVien.setPhongBan(data_PB.get(i).getMa());
            }
        }

        dbNhanVien.Them(nhanVien);
        clear();
    }

    private void XoaDL() {
        DBNhanVien dbNhanVien = new DBNhanVien(this);
        NhanVien nhanVien = new NhanVien();

        nhanVien.setMa(txtMaNV.getText().toString());
        nhanVien.setTen(txtTenNV.getText().toString());
        nhanVien.setNgaySinh(txtNgay.getText().toString());

        for (int i = 0; i < data_PB.size(); i++) {
            if(spin.getSelectedItem().toString() == data_PB.get(i).getTen())
            {
                nhanVien.setPhongBan(data_PB.get(i).getMa());
            }
        }

        dbNhanVien.Xoa(nhanVien);
        clear();
    }

    private void SuaDL() {
        DBNhanVien dbNhanVien = new DBNhanVien(this);
        NhanVien nhanVien = new NhanVien();

        nhanVien.setMa(txtMaNV.getText().toString());
        nhanVien.setTen(txtTenNV.getText().toString());
        nhanVien.setNgaySinh(txtNgay.getText().toString());

        for (int i = 0; i < data_PB.size(); i++) {
            if(spin.getSelectedItem().toString() == data_PB.get(i).getTen())
            {
                nhanVien.setPhongBan(data_PB.get(i).getMa());
            }
        }

        dbNhanVien.Sua(nhanVien);
        clear();
    }

    private void clear() {
        txtMaNV.setText("");
        txtTenNV.setText("");
        txtNgay.setText("");
        spin.setSelection(0);
    }

    private void setConTrol() {
        txtMaNV = findViewById(R.id.txtMaNV);
        txtTenNV = findViewById(R.id.txtTenNV);
        txtNgay = findViewById(R.id.txtNgaySinh);

        lvNV = findViewById(R.id.lvNV);

        spin = findViewById(R.id.spnPB);

        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
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
            AlertDialog.Builder builder = new AlertDialog.Builder(NVActivity.this);
            builder.setTitle("Thông báo");
            builder.setMessage("Bạn muốn thoát?");

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent( NVActivity.this, QLActivity.class);
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
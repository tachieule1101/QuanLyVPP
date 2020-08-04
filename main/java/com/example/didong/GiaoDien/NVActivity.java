package com.example.didong.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;

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

public class NVActivity extends AppCompatActivity {

    Spinner spnPB;
    Button btnThem, btnXoa, btnSua;
    EditText txtMaNV, txtTenNV, txtNgay, txtPB;
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

        ArrayAdapter<String> adapterNV = new ArrayAdapter(this, android.R.layout.simple_spinner_item, l);
        adapterNV.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        spnPB.setAdapter(adapterNV);

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
                XoaDL();
                HienThiDL();
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuaDL();
                HienThiDL();
            }
        });

        lvNV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NhanVien nhanVien = data_NV.get(position);
                txtMaNV.setText(nhanVien.getMa());
                txtTenNV.setText(nhanVien.getTen());
                txtNgay.setText(nhanVien.getTen());
                txtPB.setText(nhanVien.getTen());
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
            if(spnPB.getSelectedItem().toString() == data_PB.get(i).getTen())
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
        nhanVien.setPhongBan(txtPB.getText().toString());
        dbNhanVien.Xoa(nhanVien);
        clear();
    }

    private void SuaDL() {
        DBNhanVien dbNhanVien = new DBNhanVien(this);
        NhanVien nhanVien = new NhanVien();

        nhanVien.setMa(txtMaNV.getText().toString());
        nhanVien.setTen(txtTenNV.getText().toString());
        nhanVien.setNgaySinh(txtNgay.getText().toString());
        nhanVien.setPhongBan(txtPB.getText().toString());
        dbNhanVien.Sua(nhanVien);
        clear();
    }

    private void clear() {
        txtMaNV.setText("");
        txtTenNV.setText("");
        txtNgay.setText("");
    }

    private void setConTrol() {
        txtMaNV = findViewById(R.id.txtMaNV);
        txtTenNV = findViewById(R.id.txtTenNV);
        txtNgay = findViewById(R.id.txtNgaySinh);

        lvNV = findViewById(R.id.lvNV);

        spnPB = findViewById(R.id.spnPB);

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
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
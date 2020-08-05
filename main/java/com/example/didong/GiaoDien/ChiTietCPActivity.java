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

import com.example.didong.Database.DBCapPhat;
import com.example.didong.Database.DBNhanVien;
import com.example.didong.Database.DBPhongBan;
import com.example.didong.Database.DBVPP;
import com.example.didong.Model.CapPhat;
import com.example.didong.Model.NhanVien;
import com.example.didong.Model.PhongBan;
import com.example.didong.Model.VPP;
import com.example.didong.R;

import java.util.ArrayList;

public class ChiTietCPActivity extends AppCompatActivity {

    TextView tvMa;
    EditText txtSl, txtNgay;
    Spinner spnVPP, spnNV;
    Button btnXoa, btnSua;

    ArrayList<CapPhat> data_CP = new ArrayList<>();
    ArrayList<NhanVien> data_NV = new ArrayList<>();
    ArrayList<VPP> data_VPP = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_cp);
        setConTrol();
        setEvent();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setEvent() {
        String ma= getIntent().getExtras().getString("MaCP");

        DBCapPhat dbCapPhat = new DBCapPhat(this);
        data_CP = dbCapPhat.LayDL(ma);

        tvMa.setText(data_CP.get(0).getMa());
        txtNgay.setText(data_CP.get(0).getNgay());
        txtSl.setText(data_CP.get(0).getSl());

        DBVPP dbvpp = new DBVPP(this);
        data_VPP = dbvpp.LayDL();

        ArrayList<String> l = new ArrayList<>();
        for (int i = 0; i < data_VPP.size(); i++) {
            l.add(data_VPP.get(i).getTen());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,l);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        spnVPP.setAdapter(adapter);

        for (int i = 0; i < data_VPP.size(); i++) {
            if (data_VPP.get(i).getMa().equals(data_CP.get(0).getVpp()))
            {
                spnVPP.setSelection(i);
                break;
            }
        }

        DBNhanVien dbNhanVien = new DBNhanVien(this);
        data_NV = dbNhanVien.LayDL();

        ArrayList<String> l2 = new ArrayList<>();
        for (int i = 0; i < data_NV.size(); i++) {
            l2.add(data_NV.get(i).getTen());
        }

        ArrayAdapter<String> adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item,l2);
        adapter2.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        spnNV.setAdapter(adapter2);

        for (int i = 0; i < data_NV.size(); i++) {
            if (data_NV.get(i).getMa().equals(data_CP.get(0).getNv()))
            {
                spnNV.setSelection(i);
                break;
            }
        }

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ChiTietCPActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn muốn xóa?");

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        XoaDL();
                        Intent intent = new Intent( ChiTietCPActivity.this, CPActivity.class);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(ChiTietCPActivity.this);
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
        tvMa = findViewById(R.id.tvMaCP);
        txtNgay = findViewById(R.id.txtNgayCP);
        txtSl = findViewById(R.id.txtSL);

        spnVPP = findViewById(R.id.spnVPP);
        spnNV = findViewById(R.id.spnNV);

        btnSua = findViewById(R.id.btnSua);
        btnXoa = findViewById(R.id.btnXoa);
    }

    private void XoaDL() {
        DBCapPhat dbCapPhat = new DBCapPhat(this);
        CapPhat capPhat = new CapPhat();

        capPhat.setMa(tvMa.getText().toString());
        capPhat.setNgay(txtNgay.getText().toString());
        capPhat.setSl(txtSl.getText().toString());

        capPhat.setVpp(spnVPP.getSelectedItem().toString());
        capPhat.setNv(spnNV.getSelectedItem().toString());

        dbCapPhat.Xoa(capPhat);
    }

    private void SuaDL() {
        DBCapPhat dbCapPhat = new DBCapPhat(this);
        CapPhat capPhat = new CapPhat();

        capPhat.setMa(tvMa.getText().toString());
        capPhat.setNgay(txtNgay.getText().toString());
        capPhat.setSl(txtSl.getText().toString());

        capPhat.setVpp(spnVPP.getSelectedItem().toString());
        capPhat.setNv(spnNV.getSelectedItem().toString());
        dbCapPhat.Sua(capPhat);
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
            AlertDialog.Builder builder = new AlertDialog.Builder(ChiTietCPActivity.this);
            builder.setTitle("Thông báo");
            builder.setMessage("Bạn muốn thoát?");

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent( ChiTietCPActivity.this, CPActivity.class);
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
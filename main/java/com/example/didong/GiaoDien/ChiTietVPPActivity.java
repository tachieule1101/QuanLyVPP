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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.didong.Database.DBPhongBan;
import com.example.didong.Database.DBVPP;
import com.example.didong.Model.PhongBan;
import com.example.didong.Model.VPP;
import com.example.didong.R;

import java.util.ArrayList;

public class ChiTietVPPActivity extends AppCompatActivity {

    TextView tvMa;
    EditText txtTen, txtDVT, txtGia;
    Button btnXoa, btnSua;
    ArrayList<VPP> data_VPP = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_vpp);

        setConTrol();
        setEvent();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setEvent() {
        String ma= getIntent().getExtras().getString("MaVPP");

        DBVPP dbvpp = new DBVPP(this);
        data_VPP = dbvpp.LayDL(ma);
        tvMa.setText(data_VPP.get(0).getMa());
        txtTen.setText(data_VPP.get(0).getTen());
        txtDVT.setText(data_VPP.get(0).getDvt());
        txtGia.setText(data_VPP.get(0).getGia());

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ChiTietVPPActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn muốn xóa?");

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        XoaDL();
                        Intent intent = new Intent( ChiTietVPPActivity.this, VPPActivity.class);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(ChiTietVPPActivity.this);
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
        tvMa = findViewById(R.id.tvMaVPP);
        txtTen = findViewById(R.id.txtCTTenVPP);
        txtDVT = findViewById(R.id.txtDVT);
        txtGia = findViewById(R.id.txtGia);

        btnSua = findViewById(R.id.btnSua);
        btnXoa = findViewById(R.id.btnXoa);
    }

    private void XoaDL() {
        DBVPP dbvpp = new DBVPP(this);
        VPP vpp = new VPP();

        vpp.setMa(tvMa.getText().toString());
        vpp.setTen(txtTen.getText().toString());
        vpp.setDvt(txtDVT.getText().toString());
        vpp.setGia(txtGia.getText().toString());
        dbvpp.Xoa(vpp);
    }

    private void SuaDL() {
        DBVPP dbvpp = new DBVPP(this);
        VPP vpp = new VPP();

        vpp.setMa(tvMa.getText().toString());
        vpp.setTen(txtTen.getText().toString());
        vpp.setDvt(txtDVT.getText().toString());
        vpp.setGia(txtGia.getText().toString());
        dbvpp.Sua(vpp);
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
            AlertDialog.Builder builder = new AlertDialog.Builder(ChiTietVPPActivity.this);
            builder.setTitle("Thông báo");
            builder.setMessage("Bạn muốn thoát?");

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent( ChiTietVPPActivity.this, VPPActivity.class);
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
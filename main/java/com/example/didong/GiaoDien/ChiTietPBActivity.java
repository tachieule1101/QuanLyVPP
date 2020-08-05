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
import com.example.didong.Model.PhongBan;
import com.example.didong.R;

import java.util.ArrayList;

public class ChiTietPBActivity extends AppCompatActivity {

    TextView tvMa;
    EditText txtTen;
    Button btnXoa, btnSua;
    ArrayList<PhongBan> data_PB = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_pb);
        setConTrol();
        setEvent();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setEvent() {
        String ma= getIntent().getExtras().getString("MaPB");

        DBPhongBan dbPhongBan = new DBPhongBan(this);
        data_PB = dbPhongBan.LayDL(ma);
        tvMa.setText(data_PB.get(0).getMa());
        txtTen.setText(data_PB.get(0).getTen());

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ChiTietPBActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn muốn xóa?");

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        XoaDL();
                        Intent intent = new Intent( ChiTietPBActivity.this, PBActivity.class);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(ChiTietPBActivity.this);
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
        tvMa = findViewById(R.id.tvMaPB);
        txtTen = findViewById(R.id.txtCTTenPB);

        btnSua = findViewById(R.id.btnSua);
        btnXoa = findViewById(R.id.btnXoa);
    }
    
    private void XoaDL() {
        DBPhongBan dbPhongBan = new DBPhongBan(this);
        PhongBan phongBan = new PhongBan();

        phongBan.setMa(tvMa.getText().toString());
        phongBan.setTen(txtTen.getText().toString());
        dbPhongBan.Xoa(phongBan);
    }

    private void SuaDL() {
        DBPhongBan dbPhongBan = new DBPhongBan(this);
        PhongBan phongBan = new PhongBan();

        phongBan.setMa(tvMa.getText().toString());
        phongBan.setTen(txtTen.getText().toString());
        dbPhongBan.Sua(phongBan);
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
            AlertDialog.Builder builder = new AlertDialog.Builder(ChiTietPBActivity.this);
            builder.setTitle("Thông báo");
            builder.setMessage("Bạn muốn thoát?");

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent( ChiTietPBActivity.this, PBActivity.class);
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
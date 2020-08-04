package com.example.didong.GiaoDien;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.didong.Database.DBPhongBan;
import com.example.didong.Model.PhongBan;
import com.example.didong.R;

import java.util.ArrayList;

public class ChiTietPBActivity extends AppCompatActivity {

    EditText txtMaPB, txtTenPB;
    ArrayList<PhongBan> data_PB = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_chi_tiet_pb);
        setConTrol();
        setEvent();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    private void setEvent() {
        String ma= getIntent().getExtras().getString("ma");

        DBPhongBan dbPhongBan = new DBPhongBan(this);
        data_PB = dbPhongBan.LayDL(ma);
        txtMaPB.setText(data_PB.get(0).getMa());
        txtTenPB.setText(data_PB.get(0).getTen());
    }

    private void setConTrol() {
        txtMaPB = findViewById(R.id.txtMaPB);
        txtTenPB = findViewById(R.id.txtTenPB);
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

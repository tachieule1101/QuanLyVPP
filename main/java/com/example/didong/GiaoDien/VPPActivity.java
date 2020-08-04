package com.example.didong.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.didong.Apdapter.CustomAdapterPB;
import com.example.didong.Apdapter.CustomAdapterVPP;
import com.example.didong.Database.DBPhongBan;
import com.example.didong.Database.DBVPP;
import com.example.didong.Model.VPP;
import com.example.didong.R;

import java.util.ArrayList;

public class VPPActivity extends AppCompatActivity {

    Button btnThem, btnXoa, btnSua;
    EditText txtMaVPP, txtTenVPP, txtDVT, txtGia;
    ListView lvVPP;
    boolean ngonngu = true;

    CustomAdapterVPP apdapter;
    ArrayList<VPP> data_VPP = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v_p_p);

        setConTrol();
        setEvent();
    }

    private void setEvent() {

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

        lvVPP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VPP vpp = data_VPP.get(position);
                txtMaVPP.setText(vpp.getMa());
                txtTenVPP.setText(vpp.getTen());
                txtDVT.setText(vpp.getDvt());
                txtGia.setText(vpp.getGia());
            }
        });
    }

    private void HienThiDL() {
        DBVPP dbvpp = new DBVPP(this);

        data_VPP = dbvpp.LayDL();
        apdapter = new CustomAdapterVPP(this, R.layout.activity_lv_vpp, data_VPP);
        lvVPP.setAdapter(apdapter);
    }

    private void ThemDL() {
        DBVPP dbvpp = new DBVPP(this);
        VPP vpp = new VPP();

        vpp.setMa(txtMaVPP.getText().toString());
        vpp.setTen(txtTenVPP.getText().toString());
        vpp.setDvt(txtDVT.getText().toString());
        vpp.setGia(txtGia.getText().toString());
        dbvpp.Them(vpp);
        clear();
    }

    private void XoaDL() {
        DBVPP dbvpp = new DBVPP(this);
        VPP vpp = new VPP();

        vpp.setMa(txtMaVPP.getText().toString());
        vpp.setTen(txtTenVPP.getText().toString());
        vpp.setDvt(txtDVT.getText().toString());
        vpp.setGia(txtGia.getText().toString());
        dbvpp.Xoa(vpp);
        clear();
    }

    private void SuaDL() {
        DBVPP dbvpp = new DBVPP(this);
        VPP vpp = new VPP();

        vpp.setMa(txtMaVPP.getText().toString());
        vpp.setTen(txtTenVPP.getText().toString());
        vpp.setDvt(txtDVT.getText().toString());
        vpp.setGia(txtGia.getText().toString());
        dbvpp.Sua(vpp);
        clear();
    }

    private void clear() {
        txtMaVPP.setText("");
        txtTenVPP.setText("");
        txtDVT.setText("");
        txtGia.setText("");
    }

    private void setConTrol() {
        txtMaVPP = findViewById(R.id.txtMaVPP);
        txtTenVPP = findViewById(R.id.txtTenVPP);
        txtDVT = findViewById(R.id.txtDVT);
        txtGia = findViewById(R.id.txtGia);

        lvVPP = findViewById(R.id.lvVPP);

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
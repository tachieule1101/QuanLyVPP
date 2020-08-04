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
import com.example.didong.Database.DBPhongBan;
import com.example.didong.Model.PhongBan;
import com.example.didong.R;

import java.util.ArrayList;

public class PBActivity extends AppCompatActivity {

    Button btnThem, btnXoa, btnSua;
    EditText txtMaPB, txtTenPB;
    ListView lvPB;
    boolean ngonngu = true;

    CustomAdapterPB apdapter;
    ArrayList<PhongBan> data_PB = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_b);

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

        lvPB.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PhongBan phongBan = data_PB.get(position);
                txtMaPB.setText(phongBan.getMa());
                txtTenPB.setText(phongBan.getTen());
            }
        });
    }

    private void HienThiDL() {
        DBPhongBan dbPhongBan = new DBPhongBan(this);

        data_PB = dbPhongBan.LayDL();
        apdapter = new CustomAdapterPB(this, R.layout.activity_lv_pb, data_PB);
        lvPB.setAdapter(apdapter);
    }

    private void ThemDL() {
        DBPhongBan dbPhongBan = new DBPhongBan(this);
        PhongBan phongBan = new PhongBan();

        phongBan.setMa(txtMaPB.getText().toString());
        phongBan.setTen(txtTenPB.getText().toString());
        dbPhongBan.Them(phongBan);
        clear();
    }

    private void XoaDL() {
        DBPhongBan dbPhongBan = new DBPhongBan(this);
        PhongBan phongBan = new PhongBan();

        phongBan.setMa(txtMaPB.getText().toString());
        phongBan.setTen(txtTenPB.getText().toString());
        dbPhongBan.Xoa(phongBan);
        clear();
    }

    private void SuaDL() {
        DBPhongBan dbPhongBan = new DBPhongBan(this);
        PhongBan phongBan = new PhongBan();

        phongBan.setMa(txtMaPB.getText().toString());
        phongBan.setTen(txtTenPB.getText().toString());
        dbPhongBan.Sua(phongBan);
        clear();
    }

    private void clear() {
        txtMaPB.setText("");
        txtTenPB.setText("");
    }

    private void setConTrol() {
        txtMaPB = findViewById(R.id.txtMaPB);
        txtTenPB = findViewById(R.id.txtTenPB);

        lvPB = findViewById(R.id.lvPB);

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
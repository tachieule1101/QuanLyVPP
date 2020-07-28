package tdc.edu.vn.myapplication.GiaoDien;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import tdc.edu.vn.myapplication.Adapter.CustomApdapterPB;
import tdc.edu.vn.myapplication.DataBase.DBPhongBan;
import tdc.edu.vn.myapplication.Model.PhongBan;
import tdc.edu.vn.myapplication.R;

public class PBActivity extends AppCompatActivity {

    Button btnThem,btnXoa,btnSua;
    EditText txtMa, txtTen;
    ListView lvDanhSachPB;
    boolean ngonngu=true;

    CustomApdapterPB apdapter ;
    ArrayList<PhongBan> data_PB = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_phong_ban );
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
        btnXoa.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XoaDL();
                HienThiDL();
            }
        } );

        btnSua.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuaDL();
                HienThiDL();
            }
        } );



        lvDanhSachPB.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PhongBan PhongBan = data_PB.get( position );
                txtMa.setText( PhongBan.getMa() );

                txtTen.setText( PhongBan.getTen() );

            }
        } );
    }

    private  void HienThiDL()
    {
        DBPhongBan dbPhongBan = new DBPhongBan(this);
        data_PB = dbPhongBan.LayDL();
        apdapter = new CustomApdapterPB(this, R.layout.activity_lv, data_PB);
        lvDanhSachPB.setAdapter(apdapter);
    }

    private  void ThemDL()
    {
        DBPhongBan dbPhongBan = new DBPhongBan(this);

        PhongBan PhongBan = new PhongBan();
        PhongBan.setMa(txtMa.getText().toString());
        PhongBan.setTen(txtTen.getText().toString());

        dbPhongBan.Them(PhongBan);
        clear();
        Toast.makeText( this, "Thêm thành công", Toast.LENGTH_SHORT ).show();

    }
    private  void XoaDL()
    {
        DBPhongBan dbPhongBan = new DBPhongBan(this);

        PhongBan PhongBan = new PhongBan();
        PhongBan.setMa(txtMa.getText().toString());
        PhongBan.setTen(txtTen.getText().toString());

        dbPhongBan.Xoa(PhongBan);
        clear();
        Toast.makeText( this, "Xóa thành công", Toast.LENGTH_SHORT ).show();


    }
    private  void SuaDL()
    {
        DBPhongBan dbPhongBan = new DBPhongBan(this);

        PhongBan PhongBan = new PhongBan();
        PhongBan.setMa(txtMa.getText().toString());
        PhongBan.setTen(txtTen.getText().toString());

        dbPhongBan.Sua(PhongBan);
        clear();
        Toast.makeText( this, "Sửa thành công", Toast.LENGTH_SHORT ).show();


    }
    private  void clear()
    {
        txtMa.setText("");

        txtTen.setText("");


    }

    private void setConTrol() {
        btnThem = findViewById(R.id.btnThem);
        txtMa = findViewById(R.id.txtMa);
        txtTen = findViewById(R.id.txtTen);

        lvDanhSachPB = findViewById(R.id.lvDanhSach);

        //
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate( R.menu.main,menu );
        return super.onCreateOptionsMenu( menu );
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            //case R.id.mngrid:

        }
        return true;
    }
}

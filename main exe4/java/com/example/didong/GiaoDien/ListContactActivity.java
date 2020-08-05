//package com.example.didong.GiaoDien;
//
//import android.os.Bundle;
//import android.widget.ListView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.didong.Apdapter.CustomAdapterContact;
//import com.example.didong.R;
//
//import java.util.ArrayList;
//
//public class ListContactActivity extends AppCompatActivity {
//    ListView lvContact;
//    ArrayList<Contact> data_Contact = new ArrayList<>();
//    CustomAdapterContact adapterContact;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list_contact);
//
//
//        setControl();
//        setEvent();
//    }
//
//    private void setEvent() {
//        getdata();
//
//        adapterContact = new CustomAdapterContact(this,R.layout.item,data_Contact);
//        lvContact.setAdapter(adapterContact);
//
//    }
//    private void getdata(){
//        data_Contact.add(new Contact("123452"));
//    }
//    private void setControl() {
//        lvContact = findViewById(R.id.textView);
//    }
//}
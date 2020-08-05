package com.example.didong.GiaoDien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.didong.R;

public class Contact extends AppCompatActivity {
    private  static final int REQUEST_CALL = 1;
    private EditText editName, editNumber;
    ImageButton btCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        setControl();
        setEvent();


    }

    private void setEvent() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CONTACTS, Manifest.permission.READ_CONTACTS}, PackageManager.PERMISSION_GRANTED);
        btCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                makePhoneCall();

            }

        });
    }

    private void setControl() {
        editName = findViewById(R.id.editName);
        editNumber = findViewById(R.id.editNumber);
        btCall = findViewById(R.id.bt_call);
    }

    private void makePhoneCall() {
        String number = editNumber.getText().toString();
        if(number.trim().length() > 0)
        {
            if (ContextCompat.checkSelfPermission(Contact.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(Contact.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            }else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }
        else
        {
            Toast.makeText( Contact.this, "Enter Phone number",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }else{
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public  void getNameButton(View view)
    {
        try {
            Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(editNumber.getText().toString()));
            Cursor cursor = getContentResolver().query(uri, new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME},null,null,null);

            String stringContactName = "INVALID";
            if (cursor != null)
            {
                if (cursor.moveToFirst())
                {
                    stringContactName = cursor.getString(0);
                }
            }
            editName.setText(stringContactName);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void getNumberButton(View view){

        try {
            Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.TYPE},
                    "DISPLAY_NAME = '"+ editName.getText().toString() + "'",null,null);
            cursor.moveToFirst();
            editNumber.setText(cursor.getString(0));
        }catch (Exception e){
            e.printStackTrace();
            editNumber.setText("NA");
        }
    }
}
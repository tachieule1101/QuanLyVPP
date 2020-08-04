package com.example.didong.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.example.didong.R;

public class MainActivity extends AppCompatActivity {

    AnimationDrawable move;
    ImageView imgLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trang_chu);

        setConTrol();
        setEvent();
    }

    private void setEvent() {
        imgLoad.setBackgroundResource(R.drawable.animation);
        move = (AnimationDrawable) imgLoad.getBackground();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, QLActivity.class);
                startActivity(i);
                finish();
            }
        }, 1000);
    }

    private void setConTrol() {
        imgLoad = (ImageView) findViewById(R.id.imgLoad);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        move.start();
        super.onWindowFocusChanged(hasFocus);
    }
}
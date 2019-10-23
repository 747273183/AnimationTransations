package com.example.animationtransations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_switch:
                startActivity(new Intent(MainActivity.this, RevealActivity.class));
                break;
            case R.id.btn_scense:
                break;
        }
    }


}

package com.example.animationtransations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.animationtransations.activity.FirstActivity;
import com.example.animationtransations.reveal.RevealActivity;
import com.example.animationtransations.scene.SceneActivity;

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
                startActivity(new Intent(MainActivity.this, SceneActivity.class));
                break;
            case R.id.btn_activity:
                startActivity(new Intent(MainActivity.this, FirstActivity.class));
                break;
        }
    }


}

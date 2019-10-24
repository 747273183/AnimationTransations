package com.example.animationtransations.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Transition;
import android.widget.ImageView;

import com.example.animationtransations.R;

public class SecondActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ImageView imageView=findViewById(R.id.img);
        imageView.setTransitionName("img");

        Intent intent = getIntent();
        int resID = intent.getIntExtra("resID", -1);
        imageView.setImageResource(resID);

        //设置进场动画和出场动画(设置或不设置一样的)
 /*       Transition transition=new Explode();
        getWindow().setEnterTransition(transition);
        getWindow().setExitTransition(transition);*/

    }
}

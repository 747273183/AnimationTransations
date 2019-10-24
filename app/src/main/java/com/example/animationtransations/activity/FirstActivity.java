package com.example.animationtransations.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Transition;
import android.util.Pair;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.animationtransations.R;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onClick(View view) {
        int resID=-1;
        switch (view.getId())
        {
            case  R.id.img1:
                resID=R.drawable.pic1;
                break;
            case  R.id.img2:
                resID=R.drawable.pic2;
                break;
            case  R.id.img3:
                resID=R.drawable.pic3;
                break;
            case  R.id.img4:
                resID=R.drawable.pic4;
                break;
        }

        Intent intent=new Intent(this,SecondActivity.class);
        intent.putExtra("resID",resID);

        //自定义转场动画
        Transition transition=new Explode();
        //排除状态栏变化
        transition.excludeTarget(android.R.id.statusBarBackground,true);

        getWindow().setEnterTransition(transition);
        getWindow().setExitTransition(transition);

      /*  getWindow().getReenterTransition();//添加再次进入的效果
        getWindow().setSharedElementEnterTransition(transition);//为共享元素添加进场效果
        getWindow().setSharedElementExitTransition(transition);//为共享元素添加离场效果*/

        //设置转场动画
        //设置共享元素(让视觉更具有连续性)
        Pair<View, String> shareElement = Pair.create(view, "img");
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,shareElement);
        startActivity(intent,options.toBundle());
    }
}

package com.example.animationtransations.reveal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.animationtransations.R;

public class RevealActivity extends AppCompatActivity {
    private CheckBox cb_play_animation;
    private View mView;
    private boolean isPlayAnimation;
    private static final String TAG = "RevealActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal);

        cb_play_animation= findViewById(R.id.cb_play_animation);
        mView=findViewById(R.id.view);

        cb_play_animation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isPlayAnimation=b;
                Log.d(TAG, "isPlayAnimation="+isPlayAnimation);
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onClick(View view) {
        
        switch (view.getId())
        {
            case   R.id.btn_switch:
                Log.d(TAG, "onClick: ");
                handleChangeVisibility(isPlayAnimation);
                break;

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void handleChangeVisibility(boolean isPlayAnimation) {
        boolean shown = mView.isShown();
        Log.d(TAG, "shown= "+shown+",isPlayAnimation="+isPlayAnimation);
        if (isPlayAnimation)
        {
            if (shown)
            {
                revealExit();
            }
            else
            {
                revealEnter();
            }
        }
        else
        {
            if (shown)
            {
                mView.setVisibility(View.INVISIBLE);
            }
            else
            {
                mView.setVisibility(View.VISIBLE);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    //揭露动画进入
    private void revealEnter() {
        int width = mView.getWidth();
        int height = mView.getHeight();
        Animator animator= ViewAnimationUtils.createCircularReveal(mView,width,height,0, (float) Math.hypot(width,height));
//        animator.setDuration(5000);
        mView.setVisibility(View.VISIBLE);
        animator.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    //揭露动画退出
    private void revealExit() {
        int width = mView.getWidth();
        int height = mView.getHeight();
        Animator animator= ViewAnimationUtils.createCircularReveal(mView,width,height,(float) Math.hypot(width,height),0);
        animator.setDuration(5000);

        //动画播放完成后隐藏mView
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mView.setVisibility(View.INVISIBLE);
            }
        });
        animator.start();
    }
}

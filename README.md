# 转场动画
## 第一章 课程介绍
### 1-1 课程介绍
#### 什么是转场
        我们可以把手机的屏幕看做一个舞台,在这个舞台上不同的UI元素进入和进出,如果我们把舞台上某个
        特定的状态叫做一个场景,那不同的场景发生切换的时候就叫做转场.
#### 转场的好处
        如果在转场的时候不演示动画,那么这个切换就是瞬间的,突兀的.
#### 学习目标
        掌握定义转场动画的方法
#### 学习内容
- 定义转场动画的作用
        转场动画能够提示视觉连续性,那什么是视觉连续性呢?               
![什么是视觉连续性1](/readme/img/a0.gif)
![什么是视觉连续性2](/readme/img/a1.gif)        
**让用户可以感知到的变化的过程,就叫做视觉连续性.**
- 定义转场动画的时机
- 定义转场动画的方法       
#### 视觉连续性的两个好处
1. 动的东西,因为是动画,它能吸引用户的注意力.
也就是说我们可以合理的控制用户的聚焦点,让用户把注意力合理的放在这个变化的部分.
2. 合理设置的动画的效果,能够引导用户,让用户知道手机屏幕上的这个UI元素它从哪里来
   到哪里去,发生了怎么样的变化.它能提供视觉的连续性,从而让用户更流畅的使用我们的应用程序
#### 在什么样的时机下添加转场动画呢?
- 视觉状态改变            
  视觉状态的改变并不只是手机的屏幕发生了切换,比如activity的切换那当然是转场,单一视图当它的状态
  发生变化时,比如说它的背景从绿色变成蓝色,那么它的视觉上就已经发生了变化了.
  只要是视觉的状态发生了变化,我们就可以应用这个转场动画.      
  **a.** 单个视图      
  **b.** 单个布局       
  **c.** actity之间的跳转
- 
## 第二章 揭露动画
### 2-1 揭露动画
1. 介绍       
   当单个视图的状态发生变化时,我们可以使用带有揭露效果的动画,来展示这个变化的过程.
2. 代码实践     
   为单个视图的改变添加揭露效果.      
   ![揭露动画效果图](/readme/img/a2.gif)       
```
package com.example.animationtransations;

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

```
## 第三章 多视图的转场动画
### 3-1 转场动画框架介绍
### 3-2 转场动画应用
### 3-3 自定义转场效果
## 第四章 Activity间的转场动画
### 4-1 转场动画-理论基础
### 4-2 转场动画-代码实践
## 第五章 总结
### 5-1 课程总结

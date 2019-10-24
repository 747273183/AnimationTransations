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
之前我们学习的视图动画系统和属性动画系统都是针对单一视图的.      
而在转场动画当中一个场景下有多个视图,如何让多个视图产生不同的运动效果.        
Android中提供了转场动画框架:      
android.transition      
- Scene     
  **场景**.我们知道屏幕上的每一屏画面都是一棵视图树,我们可以把一棵视图树,       
  以及这棵视图树所有状态的那一瞬间叫做一个场景.
  
 **如何定义场景**.可以使用Scene.getSceneForLayout(sceneRoot,R.layout.sence_overview,this)
  
- Transition    
  **转换**.       
  (1)Fade:淡入淡出      
  (2)ChangeBounds:改变边界,比如位置长高等.         
  (3)AutoTransition:Android预定义的动画效果.如果我们不指定任何自定义的Transition,
  那么Android就会使用这个默认的Transition效果来应用场景的变化.       
  **如何定义Transition**            
  TransitionInflater.from(getBaseContext()).inflateTransition(R.transition.transition);     
  这个transition是和视图动画中的Animation以及属性动画中的Animator是类似的,定义的方式也都同时有两种:       
  一种就是从资源文件中加载,另一种就是在java代码中直接定义.**我们推荐的是在资源文件定义这个动画效果**.       
  **原因有两个**:**一是**在资源文件中定义的动画效果通用性更好,不同的java类中都可以加载同一个资源文件;**第二就是**     
  复杂性的控制,随着我们学习的深入,我们定义的动画可能越来越复杂,如果使用java代码定义,那么这个代码可能会非常长,而且      
  逻辑非常复杂.
- TransitionManager         
  **有了场景对象,也有了变换对象,接下来就可以转场了**.         
  TransitionManager.go(mTragetScene,transition);        
  第一个参数是跳转到的那个场景,第二个参数是变化类型,是可选的,如果没有指定,使用默认的AutoTransition
### 3-2 转场动画应用
1. 效果展示     
   它涉及到一个java类SceneActivity,三个布局文件:activity_sence.xml       
   ![activity_sence.xml](/readme/img/a4.png)        
   第一个场景:scene_overview.xml         
   ![activity_sence.xml](/readme/img/a5.png)
   ```
       <?xml version="1.0" encoding="utf-8"?>
        <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical">

        <ImageView
        android:id="@+id/iv_big"
        android:layout_width="400dp"
        android:layout_height="300dp"
        android:src="@drawable/chang_bai" />

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:gravity="center"
                android:orientation="horizontal">
        
                <TextView
                    android:id="@+id/tv_title"
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:text="长白山脉"
                    android:textSize="20sp"
                    android:textStyle="bold" />
        
                <ImageButton
                    android:id="@+id/ib_info"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:onClick="onClick"
                    android:src="@drawable/ic_info_black_24dp" />
        
            </LinearLayout>
        
            <TextView
                android:id="@+id/tv_area"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="吉林省东南部地区" />
        </LinearLayout>
   ```
   第二个场景:scene_info.xml         
   ![activity_sence.xml](/readme/img/a6.png)
   ```
       <?xml version="1.0" encoding="utf-8"?>
            <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:orientation="vertical">
    
        <ImageView
            android:id="@+id/iv_big"
            android:layout_width="300dp"
            android:layout_height="200dp"
            android:src="@drawable/chang_bai" />
    
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:gravity="center"
            android:orientation="horizontal">
    
            <TextView
                android:id="@+id/tv_title"
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:text="长白山脉"
                android:textSize="20sp"
                android:textStyle="bold" />
    
            <ImageButton
                android:id="@+id/ib_close"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:onClick="onClick"
                android:src="@drawable/ic_close_black_24dp" />
    
        </LinearLayout>
    
        <TextView
            android:id="@+id/tv_area"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="吉林省东南部地区"
            android:textSize="16sp" />
    
        <ScrollView
            android:layout_width="match_parent"
            android:layout_height="wrap_content">
    
            <TextView
                android:id="@+id/tv_introduce"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="长白山脉位于吉林省白山市东南部，是鸭绿江、松花江和图们江的发源地。是中国满族的发祥地和满族文化圣山。长白山脉的“长白”二字还有一个美好的寓意，即为长相守，到白头，代表着人们对忠贞与美满爱情的向往与歌颂。长白山最早见于中国4000多年前的文字记载中，《山海经》称“不咸山”，北魏称“徒太山”，唐称“太白山”，金始称“长白山”。" />
        </ScrollView>
    
    
    </LinearLayout>
   ```
2. java代码
   ```
       package com.example.animationtransations;

        import androidx.annotation.RequiresApi;
        import androidx.appcompat.app.AppCompatActivity;
        
        import android.os.Build;
        import android.os.Bundle;
        import android.transition.Scene;
        import android.transition.TransitionManager;
        import android.view.View;
        import android.view.ViewGroup;
        
        public class SceneActivity extends AppCompatActivity {

        private Scene mOverViewScene;
        private Scene mInfoScene;
    
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_scene);
    
            ViewGroup sceneRoot=findViewById(R.id.scense_root);//获得舞台
    
                mOverViewScene= Scene.getSceneForLayout(sceneRoot,R.layout.scene_overview,getBaseContext());
                mInfoScene = Scene.getSceneForLayout(sceneRoot, R.layout.scene_info, getBaseContext());
    
                //默认是mOverViewScene场景
                TransitionManager.go(mOverViewScene);
    
    
    
    
        }
    
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        public void onClick(View v)
        {
            switch (v.getId())
            {
                case R.id.ib_info:
                    TransitionManager.go(mInfoScene);
                    break;
                case R.id.ib_close:
                    TransitionManager.go(mOverViewScene);
                    break;
            }
        }
    }
     
   ```
### 3-3 自定义转场效果
1. 自定义转场动画 src/transition/transition.xml
   ```
   <?xml version="1.0" encoding="utf-8"?>
        <transitionSet xmlns:android="http://schemas.android.com/apk/res/android">
            <changeImageTransform android:duration="3000">
                <targets android:targetId="@id/iv_big"></targets>
            </changeImageTransform>
        <fade
            android:duration="3000"
            android:startDelay="1000">
        </fade>
       </transitionSet>
   ```
2. 设置自定义转场效果        
   ```
   Transition transition= TransitionInflater.from(getBaseContext()).inflateTransition(R.transition.transition);
    TransitionManager.go(mInfoScene,transition);
   ```
## 第四章 Activity间的转场动画
### 4-1 转场动画-理论基础

### 4-2 转场动画-代码实践
## 第五章 总结
### 5-1 课程总结

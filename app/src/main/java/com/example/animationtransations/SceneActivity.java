package com.example.animationtransations;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
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
                Transition transition= TransitionInflater.from(getBaseContext()).inflateTransition(R.transition.transition);
                TransitionManager.go(mInfoScene,transition);
                break;
            case R.id.ib_close:
                TransitionManager.go(mOverViewScene);
                break;
        }
    }
}

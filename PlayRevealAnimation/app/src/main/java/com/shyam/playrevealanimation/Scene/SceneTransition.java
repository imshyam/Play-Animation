package com.shyam.playrevealanimation.Scene;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Scene;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;

import com.shyam.playrevealanimation.R;

public class SceneTransition extends AppCompatActivity implements View.OnClickListener{

    Button bSceneTrans1, bSceneTrans2, bSceneTrans3, bSceneTrans4, bSceneTrans5, bSceneTrans6;
    ViewGroup sceneRoot;
    Scene scene0, scene1, scene2, scene3, scene4, prevScene, currScene, nextScene;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene_transition);

        sceneRoot = (ViewGroup) findViewById(R.id.rootView);
        bSceneTrans1 = (Button) findViewById(R.id.sceneTransition1);
        bSceneTrans1.setOnClickListener(this);
        bSceneTrans2 = (Button) findViewById(R.id.sceneTransition2);
        bSceneTrans2.setOnClickListener(this);
        bSceneTrans3 = (Button) findViewById(R.id.sceneTransition3);
        bSceneTrans3.setOnClickListener(this);
        bSceneTrans4 = (Button) findViewById(R.id.sceneTransition4);
        bSceneTrans4.setOnClickListener(this);
        bSceneTrans5 = (Button) findViewById(R.id.sceneTransition5);
        bSceneTrans5.setOnClickListener(this);
        bSceneTrans6 = (Button) findViewById(R.id.sceneTransition6);
        bSceneTrans6.setOnClickListener(this);
        scene0 = Scene.getSceneForLayout(sceneRoot, R.layout.scene0, this);
        scene1 = Scene.getSceneForLayout(sceneRoot, R.layout.scene1, this);
        scene2 = Scene.getSceneForLayout(sceneRoot, R.layout.scene2, this);
        scene3 = Scene.getSceneForLayout(sceneRoot, R.layout.scene3, this);
        scene4 = Scene.getSceneForLayout(sceneRoot, R.layout.scene4, this);

        scene0.enter();
        currScene = scene0;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scene, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sceneTransition1:
                nextScene = currScene == scene1 ? prevScene : scene1;
                prevScene = currScene;
                currScene = nextScene;
                TransitionManager.go(nextScene, new ChangeBounds().setInterpolator(new BounceInterpolator()).setDuration(700));
                break;
            case R.id.sceneTransition2:
                nextScene = currScene == scene2 ? prevScene : scene2;
                prevScene = currScene;
                currScene = nextScene;
                TransitionManager.go(nextScene, new ChangeBounds().setInterpolator(new OvershootInterpolator()).setDuration(700));
                break;
            case R.id.sceneTransition3:
                nextScene = currScene == scene3 ? prevScene : scene3;
                prevScene = currScene;
                currScene = nextScene;
                TransitionManager.go(nextScene, new ChangeBounds().setInterpolator(new AccelerateInterpolator()).setDuration(700));
                break;
            case R.id.sceneTransition4:
                nextScene = currScene == scene4 ? prevScene : scene4;
                prevScene = currScene;
                currScene = nextScene;
                TransitionManager.go(nextScene, new ChangeBounds().setInterpolator(new AnticipateInterpolator()).setDuration(700));
                break;
            case R.id.sceneTransition5:
                nextScene = currScene == scene1 ? prevScene : scene1;
                prevScene = currScene;
                currScene = nextScene;
                TransitionManager.go(nextScene, TransitionInflater.from(SceneTransition.this).inflateTransition(R.transition.changebounds_arcmotion));
                break;
            case R.id.sceneTransition6:
                nextScene = currScene == scene2 ? prevScene : scene2;
                prevScene = currScene;
                currScene = nextScene;
                TransitionManager.go(nextScene, TransitionInflater.from(SceneTransition.this).inflateTransition(R.transition.explode_changebounds));
                break;
        }
    }
}

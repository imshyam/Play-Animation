package com.shyam.playrevealanimation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.shyam.playrevealanimation.ActivityTransition.ActivityTransitionMain;
import com.shyam.playrevealanimation.Reveal.RevealAnimation;
import com.shyam.playrevealanimation.Scene.SceneTransition;
import com.shyam.playrevealanimation.SharedActivityTransition.SharedActivityTransitionMain;
import com.shyam.playrevealanimation.ViewTransition.ViewTransition;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button view, scene, activity, activityShared, reveal;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Play Animations");

        getWindow().setExitTransition(TransitionInflater.from(this).inflateTransition(R.transition.explode));

        view = (Button) findViewById(R.id.viewTransition);
        scene = (Button) findViewById(R.id.sceneTransition1);
        activity = (Button) findViewById(R.id.activityTransition);
        activityShared = (Button) findViewById(R.id.sharedActivityTransition);
        reveal = (Button) findViewById(R.id.reveal);

        view.setOnClickListener(this);
        scene.setOnClickListener(this);
        activity.setOnClickListener(this);
        activityShared.setOnClickListener(this);
        reveal.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        Intent intent;

        switch (v.getId()){
            case R.id.viewTransition:
                intent = new Intent(this, ViewTransition.class);
                startActivity(intent);
                break;
            case R.id.sceneTransition1:
                intent = new Intent(this, SceneTransition.class);
                startActivity(intent);
                break;
            case R.id.activityTransition:
                intent = new Intent(this, ActivityTransitionMain.class);
                startActivity(intent);
                break;
            case R.id.sharedActivityTransition:
                intent = new Intent(this, SharedActivityTransitionMain.class);
                startActivity(intent);
                break;
            case R.id.reveal:
                intent = new Intent(this, RevealAnimation.class);
                startActivity(intent);
                break;
        }
    }
}

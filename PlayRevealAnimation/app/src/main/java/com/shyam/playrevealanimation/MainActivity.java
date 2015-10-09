package com.shyam.playrevealanimation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.shyam.playrevealanimation.ActivityTransition.ActivityTransitionMain;
import com.shyam.playrevealanimation.CircularReveal.CircularReveal;
import com.shyam.playrevealanimation.Scene.SceneTransition;
import com.shyam.playrevealanimation.ViewTransition.ViewTransition;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button view, scene, activity, activityShared, circularReveal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setExitTransition(TransitionInflater.from(this).inflateTransition(R.transition.explode));

        view = (Button) findViewById(R.id.viewTransition);
        scene = (Button) findViewById(R.id.sceneTransition1);
        activity = (Button) findViewById(R.id.activityTransition);
        activityShared = (Button) findViewById(R.id.sharedActivityTransition);
        circularReveal = (Button) findViewById(R.id.circularReveal);

        view.setOnClickListener(this);
        scene.setOnClickListener(this);
        activity.setOnClickListener(this);
        activityShared.setOnClickListener(this);
        circularReveal.setOnClickListener(this);


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
                intent = new Intent(this, ActivityTransitionRename.class);
                startActivity(intent);
                break;
            case R.id.circularReveal:
                intent = new Intent(this, CircularReveal.class);
                startActivity(intent);
                break;
        }
    }
}

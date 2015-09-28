package com.shyam.playrevealanimation.ActivityTransition;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.shyam.playrevealanimation.R;

public class ActivityTransitionMain extends AppCompatActivity implements View.OnClickListener{

    Toolbar toolbar;
    Button bFadeAnim, bFadeTransitionXml, bFadeTransitionJava;
    Button bExplodeTransitionXml, bExplodeTransitionXml2, bExplodeTransitionJava;
    Button bSlideTransitionNoOverlap ,bSlideTransitionXml, bSlideTransitionJava;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transtion_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        bFadeAnim = (Button) findViewById(R.id.startFadeAnim);
        bFadeAnim.setOnClickListener(this);
        bFadeTransitionXml= (Button) findViewById(R.id.startFadeTransitionXml);
        bFadeTransitionXml.setOnClickListener(this);
        bFadeTransitionJava = (Button) findViewById(R.id.startFadeTransitionJava);
        bFadeTransitionJava.setOnClickListener(this);
        bExplodeTransitionXml = (Button) findViewById(R.id.startExplodeTransitionXml);
        bExplodeTransitionXml.setOnClickListener(this);
        bExplodeTransitionXml2 = (Button) findViewById(R.id.startExplodeTransitionXml2);
        bExplodeTransitionXml2.setOnClickListener(this);
        bExplodeTransitionJava= (Button) findViewById(R.id.startExplodeTransitionJava);
        bExplodeTransitionJava.setOnClickListener(this);
        bSlideTransitionNoOverlap= (Button) findViewById(R.id.startSlideNoOverlap);
        bSlideTransitionNoOverlap.setOnClickListener(this);
        bSlideTransitionXml= (Button) findViewById(R.id.startSlideTransitionXml);
        bSlideTransitionXml.setOnClickListener(this);
        bSlideTransitionJava= (Button) findViewById(R.id.startSlideTransitionJava);
        bSlideTransitionJava.setOnClickListener(this);

        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fade, menu);
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
        Intent intent = new Intent(ActivityTransitionMain.this, DetailsActivityTransition.class);;
        Transition transition;
        getWindow().setAllowReturnTransitionOverlap(true);
        getWindow().setAllowEnterTransitionOverlap(true);
        switch (v.getId()) {
            case R.id.startFadeAnim:

                intent.putExtra("WHAT_TO_DO", 1);

                startActivity(intent);
                //Apply entry (fade in) and exit (fade out) animation transitions.
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                break;
            case R.id.startFadeTransitionXml:

                transition =
                        TransitionInflater.from(this).
                                inflateTransition(R.transition.fade);
                getWindow().setExitTransition(transition);

                intent.putExtra("WHAT_TO_DO", 2);

                startActivity(intent, ActivityOptions
                        .makeSceneTransitionAnimation(ActivityTransitionMain.this).toBundle());
                break;
            case R.id.startFadeTransitionJava:
                //new Fade Transition
                Fade fade = new Fade();
                //exclude status bar, navigation bar and action bar
                fade.excludeTarget(android.R.id.statusBarBackground, true);
                fade.excludeTarget(android.R.id.navigationBarBackground, true);
                fade.excludeTarget(R.id.toolbar, true);
                //set duration and set to window exit transition
                fade.setDuration(500);
                getWindow().setExitTransition(fade);

                intent.putExtra("WHAT_TO_DO", 3);

                startActivity(intent, ActivityOptions
                        .makeSceneTransitionAnimation(ActivityTransitionMain.this).toBundle());
                break;
            case R.id.startExplodeTransitionXml:

                transition =
                        TransitionInflater.from(this).
                                inflateTransition(R.transition.explode);
                getWindow().setExitTransition(transition);

                intent.putExtra("WHAT_TO_DO", 4);

                startActivity(intent, ActivityOptions
                        .makeSceneTransitionAnimation(ActivityTransitionMain.this).toBundle());
                break;
            case R.id.startExplodeTransitionXml2:
                transition =
                        TransitionInflater.from(this).
                                inflateTransition(R.transition.explode2);
                getWindow().setExitTransition(transition);

                intent.putExtra("WHAT_TO_DO", 5);

                startActivity(intent, ActivityOptions
                        .makeSceneTransitionAnimation(ActivityTransitionMain.this).toBundle());
                break;
            case R.id.startExplodeTransitionJava:
                //new Explode Transition
                Explode explode = new Explode();
                //exclude status bar, navigation bar and action bar
                explode.excludeTarget(android.R.id.statusBarBackground, true);
                explode.excludeTarget(android.R.id.navigationBarBackground, true);
                explode.excludeTarget(R.id.toolbar, true);
                //set duration and set to window exit transition
                explode.setDuration(500);
                getWindow().setExitTransition(explode);

                intent.putExtra("WHAT_TO_DO", 6);

                startActivity(intent, ActivityOptions
                        .makeSceneTransitionAnimation(ActivityTransitionMain.this).toBundle());
                break;
            case R.id.startSlideNoOverlap:
                //new Explode Transition
                Slide slide = new Slide();

                //Int Values For slide edge
                //top = 48, right = 5, start = 8388611, left = 3, end = 8388613, bottom = 80
                slide.setSlideEdge(3);

                //exclude status bar, navigation bar and action bar
                slide.excludeTarget(android.R.id.statusBarBackground, true);
                slide.excludeTarget(android.R.id.navigationBarBackground, true);

                //set duration and set to window exit transition
                slide.setDuration(500);

                getWindow().setAllowReturnTransitionOverlap(false);
                getWindow().setAllowEnterTransitionOverlap(false);
                getWindow().setExitTransition(slide);

                intent.putExtra("WHAT_TO_DO", 7);

                startActivity(intent, ActivityOptions
                        .makeSceneTransitionAnimation(ActivityTransitionMain.this).toBundle());
                break;
            case R.id.startSlideTransitionXml:
                transition =
                        TransitionInflater.from(this).
                                inflateTransition(R.transition.slide);
                getWindow().setExitTransition(transition);

                intent = new Intent(ActivityTransitionMain.this, DetailsActivityTransition.class);
                intent.putExtra("WHAT_TO_DO", 8);

                startActivity(intent, ActivityOptions
                        .makeSceneTransitionAnimation(ActivityTransitionMain.this).toBundle());
                break;
            case R.id.startSlideTransitionJava:
                //new Slide Transition
                Slide slide1 = new Slide();
                //set Slide edge
                //Int Values For
                //top = 48, right = 5, start = 8388611, left = 3, end = 8388613, bottom = 80
                slide1.setSlideEdge(48);
                //exclude status bar, navigation bar
                slide1.excludeTarget(android.R.id.statusBarBackground, true);
                slide1.excludeTarget(android.R.id.navigationBarBackground, true);
                //set duration and set to window exit transition
                slide1.setDuration(500);
                getWindow().setExitTransition(slide1);

                intent = new Intent(ActivityTransitionMain.this, DetailsActivityTransition.class);
                intent.putExtra("WHAT_TO_DO", 8);

                startActivity(intent, ActivityOptions
                        .makeSceneTransitionAnimation(ActivityTransitionMain.this).toBundle());
                break;
        }
    }
}

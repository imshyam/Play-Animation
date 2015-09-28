package com.shyam.playrevealanimation.ActivityTransition;

import android.support.design.widget.Snackbar;
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

import com.shyam.playrevealanimation.R;

/**
 * Created by shyam on 26/9/15.
 */

public class DetailsActivityTransition extends AppCompatActivity {

    Toolbar toolbar;
    int extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        extra = getIntent().getIntExtra("WHAT_TO_DO", 0);

        setUpAnim();

        setContentView(R.layout.activity_details);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
    }

    private void setUpAnim() {
        Transition transition;
        getWindow().setAllowReturnTransitionOverlap(true);
        getWindow().setAllowEnterTransitionOverlap(true);
        switch (extra) {
            case 1:
                break;
            case 2:
                transition =TransitionInflater.from(this).inflateTransition(R.transition.fade);
                getWindow().setEnterTransition(transition);

                break;
            case 3:
                Fade fade = new Fade();
                fade.setDuration(500);
                fade.excludeTarget(R.id.toolbar, true);
                fade.excludeTarget(android.R.id.statusBarBackground, true);
                fade.excludeTarget(android.R.id.navigationBarBackground, true);
                getWindow().setEnterTransition(fade);
                break;
            case 4:
                transition = TransitionInflater.from(this).inflateTransition(R.transition.explode);
                getWindow().setEnterTransition(transition);

                break;
            case 5:
                transition = TransitionInflater.from(this).inflateTransition(R.transition.explode2);
                getWindow().setEnterTransition(transition);

                break;
            case 6:
                Explode explode = new Explode();
                explode.setDuration(500);
                explode.excludeTarget(R.id.toolbar, true);
                explode.excludeTarget(android.R.id.statusBarBackground, true);
                explode.excludeTarget(android.R.id.navigationBarBackground, true);
                getWindow().setEnterTransition(explode);
                break;
            case 7:
                Slide slide = new Slide();
                //Int Values For slide edge
                //top = 48, right = 5, start = 8388611, left = 3, end = 8388613, bottom = 80
                slide.setSlideEdge(3);
                slide.setDuration(500);
                slide.excludeTarget(android.R.id.statusBarBackground, true);
                slide.excludeTarget(android.R.id.navigationBarBackground, true);

                getWindow().setAllowReturnTransitionOverlap(false);
                getWindow().setAllowEnterTransitionOverlap(false);
                getWindow().setEnterTransition(slide);
                break;
            case 8:
                transition = TransitionInflater.from(this).inflateTransition(R.transition.slide);
                getWindow().setEnterTransition(transition);
                break;
            default:
                Slide slide1 = new Slide();
                //Int Values For
                //top = 48, right = 5, start = 8388611, left = 3, end = 8388613, bottom = 80
                slide1.setSlideEdge(5);
                slide1.setDuration(500);
                slide1.excludeTarget(android.R.id.statusBarBackground, true);
                slide1.excludeTarget(android.R.id.navigationBarBackground, true);
                getWindow().setEnterTransition(slide1);
                break;
        }
    }

    public void onBackPressed(){
        super.onBackPressed();
        if(extra == 1){
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
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
}

package com.shyam.playrevealanimation.ViewTransition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.Button;

import com.shyam.playrevealanimation.R;

public class ViewTransition extends AppCompatActivity implements View.OnClickListener{

    Button resize, move, both;
    boolean big = true, moveIt = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_transition);

        resize = (Button) findViewById(R.id.resize);
        resize.setOnClickListener(this);
        move = (Button) findViewById(R.id.move);
        move.setOnClickListener(this);
        both = (Button) findViewById(R.id.both);
        both.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_transition, menu);
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
        final View myView = findViewById(R.id.viewCircle);
        Float moveTo, resizeTo;
        moveTo = moveIt?400f:0f;
        resizeTo = big?.5f:1f;
        ObjectAnimator animX, animY, animXMove;
        AnimatorSet animSetXY;
        switch (v.getId()){
            case R.id.resize:
                big = big?false:true;
                animX = ObjectAnimator.ofFloat(myView, "scaleX", resizeTo);
                animY = ObjectAnimator.ofFloat(myView, "scaleY", resizeTo);
                animSetXY = new AnimatorSet();
                animSetXY.playTogether(animX, animY);
                animSetXY.start();
                break;
            case R.id.move:
                moveIt = moveIt?false:true;
                animX = ObjectAnimator.ofFloat(myView, "x", moveTo);
                animX.setInterpolator(new BounceInterpolator());
                animX.start();
                break;
            case R.id.both:
                big = big?false:true;
                moveIt = moveIt?false:true;
                animX = ObjectAnimator.ofFloat(myView, "scaleX", resizeTo);
                animY = ObjectAnimator.ofFloat(myView, "scaleY", resizeTo);
                animXMove = ObjectAnimator.ofFloat(myView, "x", moveTo);
                animSetXY = new AnimatorSet();
                animSetXY.playTogether(animX, animY, animXMove);
                animSetXY.start();
                break;
        }
    }
}

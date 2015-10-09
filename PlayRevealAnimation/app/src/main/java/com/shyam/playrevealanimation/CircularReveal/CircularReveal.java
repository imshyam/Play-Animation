package com.shyam.playrevealanimation.CircularReveal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.shyam.playrevealanimation.R;

public class CircularReveal extends AppCompatActivity {

    boolean clicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_reveal);

        final FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingActionButton.setSelected(!floatingActionButton.isSelected());
                floatingActionButton.setImageResource(floatingActionButton.isSelected() ? R.drawable.anim_vector : R.drawable.anim_vector_reverse);
                boolean reveal = floatingActionButton.isSelected() ? true : false;
                Drawable drawable = floatingActionButton.getDrawable();

                if(reveal){
                    // previously invisible view
                    View myView = findViewById(R.id.searchReveal);

// get the center for the clipping circle
                    int cx = (int) floatingActionButton.getX() + floatingActionButton.getWidth() / 2;
                    int cy = (int) (floatingActionButton.getY() + floatingActionButton.getHeight() / 2);

// get the final radius for the clipping circle
                    int finalRadius = Math.max(myView.getWidth(), myView.getHeight());

// create the animator for this view (the start radius is zero)
                    Animator anim =
                            ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, 2 * finalRadius);
                    anim.setDuration(500);

// make the view visible and start the animation
                    myView.setVisibility(View.VISIBLE);
                    anim.start();if (drawable instanceof Animatable) {
                        ((Animatable) drawable).start();
                    }
                }
                else {
                    // previously visible view
                    final View myView = findViewById(R.id.searchReveal);

// get the center for the clipping circle
                    int cx = (int) floatingActionButton.getX() + floatingActionButton.getWidth() / 2;
                    int cy = (int) (floatingActionButton.getY() + floatingActionButton.getHeight() / 2);

// get the initial radius for the clipping circle
                    int initialRadius = myView.getWidth();

// create the animation (the final radius is zero)
                    Animator anim =
                            ViewAnimationUtils.createCircularReveal(myView, cx, cy, 2 * initialRadius, 0);
                    anim.setDuration(500);

// make the view invisible when the animation is done
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            myView.setVisibility(View.INVISIBLE);
                        }
                    });

// start the animation
                    anim.start();
                    if (drawable instanceof Animatable) {
                        ((Animatable) drawable).start();
                    }
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_circular_reveal, menu);
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

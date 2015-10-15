package com.shyam.playrevealanimation.Reveal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shyam.playrevealanimation.R;

import java.util.ArrayList;
import java.util.Locale;

public class RevealAnimation extends AppCompatActivity implements View.OnClickListener{

    FloatingActionButton floatingActionButtonCircular, floatingActionButtonRect, floatingActionButtonSlide;
    View viewMoveAndReveal, viewReveal;
    Button button;
    ImageButton searchCancel, voiceSearch;
    Toolbar toolbar;
    int finalRadius, cx, cy;
    View searchReavelView;
    RelativeLayout mainView;
    boolean reveal;
    EditText searchText;
    private final int REQ_CODE_SPEECH_INPUT = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        searchText = (EditText) findViewById(R.id.textToSearch);

        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(this);

        searchCancel = (ImageButton) findViewById(R.id.searchBack);
        searchCancel.setOnClickListener(this);

        voiceSearch = (ImageButton) findViewById(R.id.voiceSearch);
        voiceSearch.setOnClickListener(this);

        viewMoveAndReveal = (View) findViewById(R.id.viewMoveReveal);
        viewMoveAndReveal.setOnClickListener(this);
        viewReveal = (View) findViewById(R.id.viewReveal);
        viewReveal.setOnClickListener(this);


        floatingActionButtonRect = (FloatingActionButton) findViewById(R.id.fabSearchRect);
        floatingActionButtonRect.setOnClickListener(this);

        floatingActionButtonSlide = (FloatingActionButton) findViewById(R.id.fabSearchSlide);
        floatingActionButtonSlide.setOnClickListener(this);

        floatingActionButtonCircular = (FloatingActionButton) findViewById(R.id.fabSearch);
        floatingActionButtonCircular.setOnClickListener(this);

        // previously invisible view
        searchReavelView = findViewById(R.id.searchReveal);
        //main View previously visible
        mainView = (RelativeLayout) findViewById(R.id.mainView);

        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Reveal");


    }

    @Override
    public void onBackPressed(){
        if(floatingActionButtonCircular.isSelected()){
            floatingActionButtonCircular.performClick();
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reveal, menu);
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
        switch (v.getId()){
            case R.id.fabSearchRect:
                floatingActionButtonRect.setClickable(false);
                floatingActionButtonCircular.setEnabled(false);
                floatingActionButtonSlide.setEnabled(false);
                floatingActionButtonRect.setSelected(!floatingActionButtonRect.isSelected());
                floatingActionButtonRect.setImageResource(floatingActionButtonRect.isSelected() ? R.drawable.anim_vector_search : R.drawable.anim_vector_search_reverse);
                reveal = floatingActionButtonRect.isSelected() ? true : false;
                Drawable drawable0 = floatingActionButtonRect.getDrawable();

                if(reveal) {
                    searchReavelView.setX(-searchReavelView.getWidth());
                    searchReavelView.setY(searchReavelView.getHeight());
                    searchReavelView.setAlpha(0.0f);
                    searchReavelView.setVisibility(View.VISIBLE);
                    searchReavelView.animate()
                            .translationX(0)
                            .translationY(0)
                            .alpha(1.0f)
                            .setDuration(700)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationStart(Animator animation) {
                                    super.onAnimationStart(animation);
                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    //On anim end
                                    //Disable children of mainView
                                    for (int i = 0; i < mainView.getChildCount(); i++) {
                                        View child = mainView.getChildAt(i);
                                        child.setEnabled(false);
                                    }
                                    floatingActionButtonRect.setClickable(true);
                                }
                            });
                }
                else{
                    searchReavelView.animate()
                            .translationX(-searchReavelView.getWidth())
                            .translationY(searchReavelView.getHeight())
                            .setDuration(700)
                            .alpha(0.0f)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationStart(Animator animation) {
                                    super.onAnimationStart(animation);
                                    //Disable children of mainView
                                    for (int i = 0; i < mainView.getChildCount(); i++) {
                                        View child = mainView.getChildAt(i);
                                        child.setEnabled(true);
                                    }
                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    //On anim end
                                    floatingActionButtonCircular.setEnabled(true);
                                    floatingActionButtonSlide.setEnabled(true);
                                    floatingActionButtonRect.setClickable(true);
                                    searchReavelView.setVisibility(View.INVISIBLE);
                                    searchReavelView.setY(0);
                                    searchReavelView.setX(0);
                                    searchReavelView.setAlpha(1.0f);
                                }
                            });
                }

                if (drawable0 instanceof Animatable) {
                    ((Animatable) drawable0).start();
                }
                break;
            case R.id.fabSearchSlide:
                floatingActionButtonSlide.setClickable(false);
                floatingActionButtonCircular.setEnabled(false);
                floatingActionButtonRect.setEnabled(false);
                floatingActionButtonSlide.setSelected(!floatingActionButtonSlide.isSelected());
                floatingActionButtonSlide.setImageResource(floatingActionButtonSlide.isSelected() ? R.drawable.anim_vector_add : R.drawable.anim_vector_add_reverse);
                reveal = floatingActionButtonSlide.isSelected() ? true : false;
                Drawable drawable1 = floatingActionButtonSlide.getDrawable();

                if(reveal) {
                    searchReavelView.setY(searchReavelView.getHeight());
                    searchReavelView.setAlpha(0.0f);
                    searchReavelView.setVisibility(View.VISIBLE);
                    searchReavelView.animate()
                            .translationY(0)
                            .alpha(1.0f)
                            .setDuration(700)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationStart(Animator animation) {
                                    super.onAnimationStart(animation);
                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    //On anim end
                                    //Disable children of mainView
                                    for (int i = 0; i < mainView.getChildCount(); i++) {
                                        View child = mainView.getChildAt(i);
                                        child.setEnabled(false);
                                    }
                                    floatingActionButtonSlide.setClickable(true);
                                }
                            });
                }
                else{
                    searchReavelView.animate()
                            .translationY(searchReavelView.getHeight())
                            .setDuration(700)
                            .alpha(0.0f)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationStart(Animator animation) {
                                    super.onAnimationStart(animation);
                                    //Disable children of mainView
                                    for (int i = 0; i < mainView.getChildCount(); i++) {
                                        View child = mainView.getChildAt(i);
                                        child.setEnabled(true);
                                    }
                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    //On anim end
                                    floatingActionButtonCircular.setEnabled(true);
                                    floatingActionButtonRect.setEnabled(true);
                                    floatingActionButtonSlide.setClickable(true);
                                    searchReavelView.setVisibility(View.INVISIBLE);
                                    searchReavelView.setY(0);
                                    searchReavelView.setAlpha(1.0f);
                                }
                            });
                }

                if (drawable1 instanceof Animatable) {
                    ((Animatable) drawable1).start();
                }
                break;
            case R.id.fabSearch:
                floatingActionButtonCircular.setClickable(false);
                floatingActionButtonSlide.setEnabled(false);
                floatingActionButtonRect.setEnabled(false);
                floatingActionButtonCircular.setSelected(!floatingActionButtonCircular.isSelected());
                floatingActionButtonCircular.setImageResource(floatingActionButtonCircular.isSelected() ? R.drawable.anim_vector_search : R.drawable.anim_vector_search_reverse);
                reveal = floatingActionButtonCircular.isSelected() ? true : false;
                Drawable drawable = floatingActionButtonCircular.getDrawable();

                // get the final radius for the clipping circle
                //hypot because we need the hypotenuse of the view as the radius
                finalRadius = (int) Math.hypot(searchReavelView.getWidth(), searchReavelView.getHeight());
                // get the center for the clipping circle
                cx = (int) floatingActionButtonCircular.getX() + floatingActionButtonCircular.getWidth() / 2;
                cy = (int) (floatingActionButtonCircular.getY() + floatingActionButtonCircular.getHeight() / 2);

                if(reveal){


// create the animator for this view (the start radius is zero)
                    Animator anim =
                            ViewAnimationUtils.createCircularReveal(searchReavelView, cx, cy, 0, finalRadius);

                    int duration = getResources().getInteger(R.integer.duration_anim);
                    anim.setDuration(duration);

// make the view visible and start the animation
                    searchReavelView.setVisibility(View.VISIBLE);
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            floatingActionButtonCircular.setClickable(true);
                            //Disable children of mainView
                            for (int i = 0; i < mainView.getChildCount(); i++) {
                                View child = mainView.getChildAt(i);
                                child.setEnabled(false);
                            }
                        }
                    });
                    anim.start();
                    if (drawable instanceof Animatable) {
                        ((Animatable) drawable).start();
                    }
                }
                else {

// create the animation (the final radius is zero)
                    Animator anim =
                            ViewAnimationUtils.createCircularReveal(searchReavelView, cx, cy, finalRadius, 0);

                    int duration = getResources().getInteger(R.integer.duration_anim);
                    anim.setDuration(duration);

                    //Enable children of mainView
                    for (int i = 0; i < mainView.getChildCount(); i++) {
                        View child = mainView.getChildAt(i);
                        child.setEnabled(true);
                    }
                    // make the view invisible when the animation is done
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            searchReavelView.setVisibility(View.INVISIBLE);
                            floatingActionButtonCircular.setClickable(true);
                            floatingActionButtonSlide.setEnabled(true);
                            floatingActionButtonRect.setEnabled(true);
                        }
                    });

                    // start the animation
                    anim.start();
                    if (drawable instanceof Animatable) {
                        ((Animatable) drawable).start();
                    }
                }
                break;
            case R.id.searchBack:
                if(floatingActionButtonCircular.isSelected()) {
                    floatingActionButtonCircular.performClick();
                }
                else if(floatingActionButtonSlide.isSelected()) {
                    floatingActionButtonSlide.performClick();
                }
                break;
            case R.id.viewReveal:
                ViewGroup.LayoutParams originalParams0 = viewMoveAndReveal.getLayoutParams();
                int cx = (mainView.getLeft() + mainView.getRight()) / 2;
                int cy = (mainView.getTop() + mainView.getBottom()) / 2;

                float finalRadius = (float) Math.hypot(mainView.getWidth(), mainView.getHeight());

                Animator anim = ViewAnimationUtils.createCircularReveal(mainView, cx, cy, 0, finalRadius);
                mainView.setBackgroundColor(ContextCompat.getColor(RevealAnimation.this, R.color.cardview_light_background));
                anim.setDuration(getResources().getInteger(R.integer.duration_anim));
                anim.setInterpolator(new AccelerateDecelerateInterpolator());
                anim.start();

                viewMoveAndReveal.setLayoutParams(originalParams0);
                break;
            case R.id.viewMoveReveal:
                final ViewGroup.LayoutParams originalParams = viewMoveAndReveal.getLayoutParams();
                Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.move_revael);
                transition.addListener(new Transition.TransitionListener() {
                    @Override
                    public void onTransitionStart(Transition transition) {
                    }

                    @Override
                    public void onTransitionEnd(Transition transition) {

                        int cx = (mainView.getLeft() + mainView.getRight()) / 2;
                        int cy = (mainView.getTop() + mainView.getBottom()) / 2;

                        float finalRadius = (float) Math.hypot(mainView.getWidth(), mainView.getHeight());

                        Animator anim = ViewAnimationUtils.createCircularReveal(mainView, cx, cy, 0, finalRadius);
                        mainView.setBackgroundColor(ContextCompat.getColor(RevealAnimation.this, R.color.blue));
                        anim.setDuration(getResources().getInteger(R.integer.duration_anim));
                        anim.setInterpolator(new AccelerateDecelerateInterpolator());
                        anim.start();

                        viewMoveAndReveal.setLayoutParams(originalParams);
                    }

                    @Override
                    public void onTransitionCancel(Transition transition) {
                    }

                    @Override
                    public void onTransitionPause(Transition transition) {

                    }

                    @Override
                    public void onTransitionResume(Transition transition) {

                    }
                });
                TransitionManager.beginDelayedTransition(mainView, transition);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                viewMoveAndReveal.setLayoutParams(layoutParams);
                break;
            case R.id.button2:
                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.coordinatorLayout), "Hi! This is a SnackBar.", Snackbar.LENGTH_LONG)
                        .setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        });

// Changing message text color
                snackbar.setActionTextColor(Color.parseColor("#ffffff"));

// Changing action button text color
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.parseColor("#26c6da"));
                snackbar.show();
                break;
            case R.id.voiceSearch:
                promptSpeechInput();
                break;
        }
    }
    /**
     * Showing google speech input dialog
     * */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "What To Search");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "Speech Not Supported.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    searchText.setText(searchText.getText().toString() + " " + result.get(0));
                }
                break;
            }

        }
    }
}

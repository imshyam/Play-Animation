package com.shyam.playrevealanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button boom, reveal;
    static Bitmap bitmap;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boom = (Button) findViewById(R.id.boom);
        reveal = (Button) findViewById(R.id.button);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        boom.setOnClickListener(this);
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
    public Bitmap getBitmapFromURL(URL src) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        Bitmap myBitmap = null;
        try {
            Log.e("Here", "dasd");
            progressDialog.show();
            URL url = src;
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            myBitmap = BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            progressDialog.hide();
            return myBitmap;
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                //fill Data

                    try {
                        bitmap = getBitmapFromURL(new URL("http://www.google.com/favicon.ico"));
                        Log.e("BitMap : ", String.valueOf(bitmap));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }


                final View myView = findViewById(R.id.viewReveal);

                if(myView.getVisibility() == View.VISIBLE){

                    // get the center for the clipping circle
                    int cx = myView.getWidth() / 2;
                    int cy = myView.getHeight() / 2;

                    // get the initial radius for the clipping circle
                    int initialRadius = myView.getWidth();

                    // create the animation (the final radius is zero)
                    Animator anim =
                            ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius, initialRadius/8);

                    // make the view invisible when the animation is done
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            myView.setVisibility(View.GONE);
                        }
                    });

                    anim.setDuration(1000);
                    // start the animation
                    anim.start();
                }
                else {
                    // get the center for the clipping circle
                    int cx = myView.getWidth() / 2;
                    int cy = myView.getHeight() / 2;

                    // get the final radius for the clipping circle
                    int finalRadius = Math.max(myView.getWidth(), myView.getHeight());

                    // create the animator for this view (the start radius is zero)
                    Animator anim =
                            ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius  );

                    // make the view visible and start the animation
                    myView.setVisibility(View.VISIBLE);
                    anim.setDuration(1000);
                    anim.start();
                }
                break;
            case R.id.boom:
                Intent intent = new Intent(this, FinalActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    public static Bitmap getBitmap() {
        return bitmap;
    }
}

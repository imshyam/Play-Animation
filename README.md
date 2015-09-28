# Play-Animation
This is an android app that covers most of existing animation in Goolge Play App and basic Material Animations.
So lets start with the basics : 

###Circular Reveal

This is easy one and explained at [Android Developer Training Notes][1]. For using this animation simply use this : 

    // previously invisible view
    View myView = findViewById(R.id.my_view);
    
    // get the center for the clipping circle
    int cx = myView.getWidth() / 2;
    int cy = myView.getHeight() / 2;
    
    // get the final radius for the clipping circle
    int finalRadius = Math.max(myView.getWidth(), myView.getHeight());
    
    // create the animator for this view (the start radius is zero)
    Animator anim =
        ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);
    
    // make the view visible and start the animation
    myView.setVisibility(View.VISIBLE);
    anim.start();
    
Make sure that visibility of view is not set to `gone`, if visiblity is set to `gone` then animation will work fine except for the very firts time, as we can't get the radius of element which is not there initially.

    View myView = findViewById(R.id.my_view);
    if(myView.getVisibility() == View.VISIBLE){
    // get the center for the clipping circle
    int cx = myView.getWidth() / 2;
    int cy = myView.getHeight() / 2;
  
    // get the initial radius for the clipping circle
    
    int initialRadius = myView.getWidth();
  
    // create the animation (the final radius is zero)
    int finalRadius = 0;
    Animator anim = 
        ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius, finalRadius);
  
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


[1]:https://developer.android.com/training/material/animations.html#Reveal

package com.raj.newapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    TextView textView1;
    TextView appInfo;
    Animation topAnim;
    Animation bottomAnim;
    Animation rightAnim;
    Animation leftAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        textView=findViewById(R.id.app_name);
        textView1=findViewById(R.id.app_name1);
        appInfo=findViewById(R.id.app_info);
        imageView=findViewById(R.id.imgView);

        //Animation
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        leftAnim = AnimationUtils.loadAnimation(this,R.anim.left_animation);
        rightAnim = AnimationUtils.loadAnimation(this,R.anim.right_animation);


        //set Animation
        imageView.setAnimation(topAnim);
        textView.setAnimation(leftAnim);
        textView1.setAnimation(rightAnim);
        appInfo.setAnimation(bottomAnim);



        /****** Create Thread that will sleep for 4 seconds****/
        Thread background = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 4 seconds
                    sleep(4*1000);

                    // After 4 seconds redirect to another intent
                    Intent i=new Intent(getBaseContext(),HomeActivity.class);
                    startActivity(i);

                    //Remove activity
                    finish();
                } catch (Exception e) {
                }
            }
        };
        // start thread
        background.start();



    }
}
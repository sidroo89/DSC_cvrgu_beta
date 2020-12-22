package com.example.dsccvrgubeta.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.dsccvrgubeta.R;

public class SplashScreen extends AppCompatActivity {
    ImageView splashImage;

    Animation sideAnim,bottomAnim;

    private static int SPLASH_TIMER=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        splashImage=findViewById(R.id.splashImage);

        //animations

        sideAnim= AnimationUtils.loadAnimation(this,R.anim.side_anim);


        //set Animations on elements

        splashImage.setAnimation(sideAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                    Intent intent=new Intent(SplashScreen.this, OnBoardingPage.class);
                    startActivity(intent);

                    finish();

            }
        },SPLASH_TIMER);



    }
}
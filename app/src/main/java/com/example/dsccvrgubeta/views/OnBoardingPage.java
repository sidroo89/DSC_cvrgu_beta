package com.example.dsccvrgubeta.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dsccvrgubeta.R;
import com.example.dsccvrgubeta.adapter.OnBoardingAdapter;
import com.example.dsccvrgubeta.models.OnBoardingModel;

import java.util.ArrayList;
import java.util.List;

public class OnBoardingPage extends AppCompatActivity {
   //initialize variables
    private ViewPager2 vpOnBoarding;
    private OnBoardingAdapter onBoardingAdapter;
   private LinearLayout dotsLayout;
    TextView[] dots;
    int currentPosition;
    ImageButton next_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboarding_page);
        vpOnBoarding=findViewById(R.id.onBoardingVp);
        next_btn=findViewById(R.id.next_Btn);
        dotsLayout=findViewById(R.id.dots);
        onBoardingAdapter=new OnBoardingAdapter();

        vpOnBoarding.setAdapter(onBoardingAdapter);
       getOnBoardingData();

       addDots(0);

       vpOnBoarding.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
           @Override
           public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
               super.onPageScrolled(position, positionOffset, positionOffsetPixels);
           }

           @Override
           public void onPageSelected(int position) {
               super.onPageSelected(position);

               currentPosition=position;
               addDots(position);
               if (position==0)
               {
                   next_btn.setImageResource(R.drawable.next_page);


               }
               else if(position==1)
               {

                   next_btn.setImageResource(R.drawable.next_page);

               }
               else if(position==2)
               {
                   next_btn.setImageResource(R.drawable.next_page);

               }
               else {
                   next_btn.setImageResource(R.drawable.tick_inside_circle);
               }

           }
       });

    }

    private void getOnBoardingData() {
        List<OnBoardingModel> onBoardingModels=new ArrayList<>();
        onBoardingModels.add(new OnBoardingModel("Getting Started","Ready to unleash the power of a community? Join us ",R.drawable.onboarding_image1));
        onBoardingModels.add(new OnBoardingModel("Worried?","DSC-CVRGU provides a platform to learn all the exciting technologies which you have been wishing for. ",R.drawable.onboarding_image2));
        onBoardingModels.add(new OnBoardingModel("1on1 Mentorship","Our core team is determined to help our students whenever they need can contact us. ",R.drawable.onboarding_image3));
        onBoardingModels.add(new OnBoardingModel("Lets Go","So what are you waiting for? Let the awesomeness begin. ",R.drawable.onboarding_image4));
        onBoardingAdapter.submitList(onBoardingModels);
    }
    public  void next(View view)
    {
        if (currentPosition==3)
        {
            startActivity(new Intent(OnBoardingPage.this,SignupPage.class));
        }
        else {
            vpOnBoarding.setCurrentItem(currentPosition+1);
        }




    }
    private void addDots(int position)
    {

        dots=new TextView[4];
        dotsLayout.removeAllViews();
        for (int i=0;i<dots.length;i++)
        {
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);

            dotsLayout.addView(dots[i]);
        }
        if(dots.length>0)
        {
            if (position==0)
            dots[position].setTextColor(getResources().getColor(R.color.onBoardingRed));

            if (position==1)
                dots[position].setTextColor(getResources().getColor(R.color.onBoardingBlue));

            if (position==2)
                dots[position].setTextColor(getResources().getColor(R.color.onBoardingYellow));

            if (position==3)
                dots[position].setTextColor(getResources().getColor(R.color.onBoardingGreen));

        }

    }



}
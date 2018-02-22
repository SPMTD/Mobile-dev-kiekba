package com.example.sanderpool.programmeren9;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;

public class OnBoardingActivity extends TutorialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//      Onboarding slides
//      First Slide
        addFragment(new Step.Builder().setTitle("This is header 1")
                .setContent("This is content")
                .setBackgroundColor(Color.parseColor("#FF0957")) // int background color
                .setDrawable(R.drawable.icon_2) // int top drawable
                .setSummary("This is summary")
                .build());
//      Second Slide
        addFragment(new Step.Builder().setTitle("This is header 2")
                .setContent("This is content")
                .setBackgroundColor(Color.parseColor("#FF0957")) // int background color
                .setDrawable(R.drawable.icon_3) // int top drawable
                .setSummary("This is summary")
                .build());
//      Third slide
        addFragment(new Step.Builder().setTitle("This is header 3")
                .setContent("This is content")
                .setBackgroundColor(Color.parseColor("#FF0957")) // int background color
                .setDrawable(R.drawable.icon_4) // int top drawable
                .setSummary("This is summary")
                .build());
    }

    @Override
    public void finishTutorial() {
        // Your implementation
        startActivity(new Intent(this, MainActivity.class));
    }
}

package com.example.sanderpool.programmeren9;

import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private LocationListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

//      Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

//        SharedPreferences sharedPreferences =
//                PreferenceManager.getDefaultSharedPreferences(this);
//        // Check if we need to display our OnboardingFragment
//        if (!sharedPreferences.getBoolean(
//                MyOnboardingFragment.COMPLETED_ONBOARDING_PREF_NAME, false)) {
//            // The user hasn't seen the OnboardingFragment yet, so show it
//            startActivity(new Intent(this, OnboardingActivity.class));
//        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void testActivity(View view) {
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }
}

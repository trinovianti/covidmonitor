package com.example.covidapp.module.OnBoarding;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.example.covidapp.R;
import com.example.covidapp.module.OnBoarding.Fragment.FragmentOnBoarding1;


public class OnBoarding extends AppCompatActivity {
    FragmentPagerAdapter adapterViewPager;
    public static ViewPager vpPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        vpPager = (ViewPager) findViewById(R.id.viewPagerOnBoarding);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

    }

    public class MyPagerAdapter extends FragmentPagerAdapter {
        private  int NUM_ITEMS = 3;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return FragmentOnBoarding1.newInstance(1, OnBoarding.this.getString(R.string.lbl_onboarding_title1), OnBoarding.this.getString(R.string.lbl_onboarding_desc1),R.color.light_green,R.drawable.ic_help1, R.drawable.ic_nexthelp1);
                case 1: // Fragment # 0 - This will show FirstFragment different tit
                    return FragmentOnBoarding1.newInstance(2, OnBoarding.this.getString(R.string.lbl_onboarding_title2), OnBoarding.this.getString(R.string.lbl_onboarding_desc2),R.color.light_green,R.drawable.ic_help2, R.drawable.ic_nexthelp2);
                case 2: // Fragment # 1 - This will show SecondFragment
                    return FragmentOnBoarding1.newInstance(3, OnBoarding.this.getString(R.string.lbl_onboarding_title3), OnBoarding.this.getString(R.string.lbl_onboarding_desc3),R.color.light_green,R.drawable.ic_help3, R.drawable.ic_nexthelp3);
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }




    }

}

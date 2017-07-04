package com.example.android.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private BackPressCloseHandler backPressCloseHandler; // 뒤로 두 번 누르면 꺼지기.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backPressCloseHandler = new BackPressCloseHandler(this);

        // Glide로 대체한다.
        if(!PreloadBitmap.isInitialized()) {
            // 아이린 미리 로드
            Context ct = getApplicationContext();
            /*BitmapFactory.Options opt = new BitmapFactory.Options();
            opt.inSampleSize = 1;
            int size = 1;*/
            int i;

            Resources res = getResources();
            int numGirls = res.getInteger(R.integer.numGirls);

            for (i = 1; i <= numGirls; i++) {
                try {
                    String imgName = "i" + String.valueOf(i);
                    int id = res.getIdentifier(imgName, "drawable", ct.getApplicationInfo().packageName);

                    /*Bitmap bitmapOriginal = BitmapFactory.decodeResource(res, id, opt);
                    Bitmap bitmapSimpleSize = Bitmap.createScaledBitmap(bitmapOriginal, bitmapOriginal.getWidth() / size, bitmapOriginal.getHeight() / size, true);
                    PreloadBitmap.addBitmap(bitmapSimpleSize);*/
                    PreloadBitmap.addBitmap(id);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

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

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }




    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
        //Returning the current tabs
            switch (position) {
                case 0:
                    Tab1Contacts tab1 = new Tab1Contacts();
                    return tab1;
                case 1:
                    Tab2Gallery tab2 = new Tab2Gallery();
                    return tab2;
                case 2:
                    Tab3RSS tab3 = new Tab3RSS();
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "연락처";
                case 1:
                    return "갤러리";
                case 2:
                    return "메모";
            }
            return null;
        }
    }

    //deleted PlaceHolderFragment class from here
}

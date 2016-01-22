package ua.co.simply.effectivetimer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ua.co.simply.effectivetimer.fragments.StatisticsFragment;
import ua.co.simply.effectivetimer.fragments.TimerFragment;


public class MainActivity extends ActionBarActivity implements ActionBar.TabListener {

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    private static final int FRAGMENT_TIMER = 0;
    private static final int FRAGMENT_STATISTICS = 1;

    private static final Map<Integer, String> FRAGMENTS_TITLES = new HashMap<Integer, String>();

    private static final List<Integer> FRAGMENT_KEYS = new LinkedList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FRAGMENT_KEYS.add(FRAGMENT_TIMER);
        FRAGMENT_KEYS.add(FRAGMENT_STATISTICS);

        FRAGMENTS_TITLES.put(FRAGMENT_TIMER, getString(R.string.fragment_title_timer));
        FRAGMENTS_TITLES.put(FRAGMENT_STATISTICS, getString(R.string.fragment_title_statistics));

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
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
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment result = null;
            Integer key = null;
            if (position < FRAGMENT_KEYS.size()){
                key = FRAGMENT_KEYS.get(position);
            }
            if (key != null){
                switch (key){
                    case FRAGMENT_TIMER:
                        result = TimerFragment.newInstance();
                        break;
                    case FRAGMENT_STATISTICS:
                        result = StatisticsFragment.newInstance();
                        break;
                    default:
                        // TODO report problem
                }
            }
            return result;
        }

        @Override
        public int getCount() {
            return FRAGMENTS_TITLES.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            CharSequence result = null;
            Integer key = null;
            if (position < FRAGMENT_KEYS.size()){
                key = FRAGMENT_KEYS.get(position);
            }
            if (key != null){
                result = FRAGMENTS_TITLES.get(key).toUpperCase(l);
            }
            return result;
        }
    }

}

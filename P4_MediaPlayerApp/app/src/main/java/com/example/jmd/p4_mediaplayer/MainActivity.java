package com.example.jmd.p4_mediaplayer;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private ArrayList<String> items;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            super.onCreateView(inflater, container, savedInstanceState);
            Bundle arguments = this.getArguments();
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            ListView listView = (ListView) rootView.findViewById(R.id.listView);

            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

            int fragmentType = arguments.getInt(ARG_SECTION_NUMBER);

            switch(fragmentType) {
                case 0:
                    // Add playlist items
                    items = new ArrayList<>(Arrays.asList("Top Charts", "New Releases", "Editor's Pick", "Vidoes", "Radio", "Listen Again", "Specials", "Featured Artists"));
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent playListIntent = new Intent(getActivity(), HomeActivity.class);
                            startActivity(playListIntent);
                        }
                    });
                    break;
                case 1:
                    // Add track items
                    items = new ArrayList<>(Arrays.asList("Artist Radio", "Mood Radio", "Retro", "LatestMusic"));
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent musicPlayerIntent = new Intent(getActivity(), RadioActivity.class);
                            startActivity(musicPlayerIntent);
                        }
                    });
                    break;
                case 2:
                    // Add album items
                    items = new ArrayList<>(Arrays.asList("Specials", "Romance", "Party", "Retro", "Popular Playlists", "Devotional", "Bollywood 90s", "Ghazal", "Festivals", "Rock", "Antakshari", "Rare Gems", "Top Charts", "Sufi", "Hip Hop", "Weddings", "EDM"));
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent albumIntent = new Intent(getActivity(), DiscoverActivity.class);
                            startActivity(albumIntent);
                        }
                    });
                    break;
                case 3:
                    // Add artist items
                    items = new ArrayList<>(Arrays.asList("Songs", "Playlists", "Albums", "Radio", "Artists", "Local Files"));
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent artistIntent = new Intent(getActivity(), MyMusicActivity.class);
                            startActivity(artistIntent);
                        }
                    });
                    break;
                default:
                    items = new ArrayList<>();
                    break;
            }
            ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(getActivity(), R.layout.list_item, items);
            listView.setAdapter(itemsAdapter);
            return rootView;
        }
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
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "HOME";
                case 1:
                    return "RADIO";
                case 2:
                    return "DISCOVER";
                case 3:
                    return "MY MUSIC";
            }
            return null;
        }
    }
}

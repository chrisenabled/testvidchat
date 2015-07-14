package com.f80prototype.verizon.protoandroid.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.f80prototype.verizon.protoandroid.fragments.VideoPagerFragment;
import com.f80prototype.verizon.protoandroid.fragments.VideoViewFragment;

/**
 * Created by chrisenabled on 7/14/15.
 */
public class VideoPagerAdapter extends FragmentPagerAdapter {

    Context mContext;

    public VideoPagerAdapter(Fragment fm, Context context) {
        super(fm.getChildFragmentManager());
        mContext = context;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;

        if(i == 0){
            fragment = VideoViewFragment.newInstance(mContext);
        }
        if(i == 1){
            fragment = VideoViewFragment.newInstance(mContext);
        }
        if(i == 2){
            fragment = VideoViewFragment.newInstance(mContext);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}

package com.f80prototype.verizon.protoandroid.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.f80prototype.verizon.protoandroid.MainActivity;
import com.f80prototype.verizon.protoandroid.R;
import com.f80prototype.verizon.protoandroid.adapters.VideoPagerAdapter;

/**
 * Created by inyanja on 7/10/15.
 */
public class VideoPagerFragment extends Fragment implements View.OnClickListener, View.OnTouchListener {

    private ViewPager mVideoViewPager;
    private VideoPagerAdapter mVideoPagerAdapter;
    private Context mContext;
    private Button resizeButton;
    VideoPagerFragmentCallbacks mCallbacks;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_video, container, false);

        resizeButton = (Button) rootView.findViewById(R.id.videoResizeButton);
        resizeButton.setOnClickListener(this);

        mVideoViewPager = (ViewPager) rootView.findViewById(R.id.video_view_pager);
        mVideoPagerAdapter = new VideoPagerAdapter(this, mContext);
        mVideoViewPager.setAdapter(mVideoPagerAdapter);
        mVideoViewPager.setCurrentItem(0);

        mVideoViewPager.setOnTouchListener(this);

        return rootView;
    }

    public void setUp(Context context){
        mContext = context;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (VideoPagerFragmentCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement VideoPagerFragmentCallbacks.");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


    @Override
    public void onClick(View view) {
        resizeButton.setVisibility(View.INVISIBLE);
        mCallbacks.onResizeButtonClicked(true);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        String TAG = MainActivity.class.getSimpleName();
        float initialX = 0f;
        float initialY = 0f;
        float finalX = 0f;
        float finalY = 0f;

        int action = motionEvent.getActionMasked();

        switch (action) {

            case MotionEvent.ACTION_DOWN:
                initialX = motionEvent.getX();
                initialY = motionEvent.getY();

                Log.d(TAG, "Action was DOWN");
                break;

            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "Action was MOVE");
                break;

            case MotionEvent.ACTION_UP:
                finalX = motionEvent.getX();
                finalY = motionEvent.getY();

                if (initialY < finalY) {
                    //Log.d(TAG, "Down to Up swipe performed");
                    resizeButton.setVisibility(View.VISIBLE);
                    mCallbacks.onResizeButtonClicked(false);
                }



                break;

            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG,"Action was CANCEL");
                break;

            case MotionEvent.ACTION_OUTSIDE:
                Log.d(TAG, "Movement occurred outside bounds of current screen element");
                break;
        }




        return false;
    }

    public static interface VideoPagerFragmentCallbacks {

        void onResizeButtonClicked(Boolean isCollapseVideo);
    }
}

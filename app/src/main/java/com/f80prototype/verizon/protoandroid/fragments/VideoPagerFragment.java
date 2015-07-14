package com.f80prototype.verizon.protoandroid.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.f80prototype.verizon.protoandroid.R;
import com.f80prototype.verizon.protoandroid.adapters.VideoPagerAdapter;

/**
 * Created by inyanja on 7/10/15.
 */
public class VideoPagerFragment extends Fragment implements View.OnClickListener {

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

        return rootView;
    }

    public void setUp(Context context){
        mContext = context;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



           /* mTextureView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent event) {
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                x1 = event.getX();
                                y1 = event.getY();
                                break;

                            case MotionEvent.ACTION_UP:
                                x2 = event.getX();
                                y2 = event.getY();

                                // if Up to Down swipe event on screen
                                if (y1 < y2) {
                                    updateTextureViewSize(mVideoWidth, mVideoHeight);
                                    MainActivity.changeViewPageParams(1);
                                    MainActivity.myConversation.setVisibility(View.VISIBLE);
                                }

                                // if Down to Up swipe event on screen
                                if (y1 > y2) {
                                    downToUp();
                                }
                                break;
                        }
                        return true;
                    }
                }

            );

        protected static void upToDown(){
            updateTextureViewSize(mVideoWidth, mVideoHeight);
            MainActivity.changeViewPageParams(1);
            MainActivity.myConversation.setVisibility(View.VISIBLE);
        }
        protected static void downToUp() {
            updateTextureViewSize(mVideoWidth, resizeHeight);
            MainActivity.changeViewPageParams(0);
        }


        public static void updateTextureViewSize(int mVideoWidth, int mVideoHeight) {
            title.setVisibility(View.INVISIBLE);
            subTitle.setVisibility(View.INVISIBLE);
            mTextureView.setLayoutParams(new RelativeLayout.LayoutParams(mVideoWidth, mVideoHeight));
        }*/
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
        mCallbacks.onResizeButtonClicked();
    }

    public static interface VideoPagerFragmentCallbacks {
        /**
         * Called when an item in the navigation drawer is selected.
         */
        void onResizeButtonClicked();
    }
}

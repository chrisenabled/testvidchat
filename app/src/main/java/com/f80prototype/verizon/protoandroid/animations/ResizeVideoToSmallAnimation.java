package com.f80prototype.verizon.protoandroid.animations;

import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;


/**
 * Created by chrisenabled on 7/14/15.
 */
public class ResizeVideoToSmallAnimation extends Animation {

    Fragment mVideoFragment;
    Fragment mChatFragment;

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        params.weight = 0f;
        mVideoFragment.getView().setLayoutParams(params);

    }

    public ResizeVideoToSmallAnimation(Fragment videoFm, Fragment chatFm){

        mVideoFragment = videoFm;
        mChatFragment = chatFm;
    }
}

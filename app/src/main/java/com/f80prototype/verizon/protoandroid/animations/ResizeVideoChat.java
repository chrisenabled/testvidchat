package com.f80prototype.verizon.protoandroid.animations;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;


/**
 * Created by chrisenabled on 7/14/15.
 */
public class ResizeVideoChat {

    static DisplayMetrics display;

    public static void ResizeVideoToSmallAnimation(View video, View chat, DisplayMetrics display){

        collapse(video);
        expand(chat);
        ResizeVideoChat.display = display;
    }

    public  static void expand(final View v) {
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        final float mToHeight = display.heightPixels/2;
        final float mFromHeight = 0;

        v.getLayoutParams().height = 0;
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                float height = (mToHeight - mFromHeight) * interpolatedTime + mFromHeight;
                ViewGroup.LayoutParams lp = v.getLayoutParams();
                lp.height = (int)height;
                v.setLayoutParams(lp);
                v.requestLayout();
            }


            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration(1000);
        v.startAnimation(a);
    }

    public  static void collapse(final View v) {

        final float mToHeight = display.heightPixels/3;
        final float mFromHeight = display.heightPixels;

        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                float height = (mToHeight - mFromHeight) * interpolatedTime + mFromHeight;
                ViewGroup.LayoutParams lp = v.getLayoutParams();
                lp.height = (int)height;
                v.setLayoutParams(lp);
                v.requestLayout();

            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration(1000);
        v.startAnimation(a);
    }
}

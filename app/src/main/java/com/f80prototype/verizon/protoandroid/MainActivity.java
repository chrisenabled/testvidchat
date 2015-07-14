package com.f80prototype.verizon.protoandroid;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.f80prototype.verizon.protoandroid.fragments.ConversationFragment;
import com.f80prototype.verizon.protoandroid.fragments.VideoPagerFragment;
import com.f80prototype.verizon.protoandroid.fragments.VideoViewFragment;


public class MainActivity extends FragmentActivity implements VideoViewFragment.OnFragmentInteractionListener,
        VideoPagerFragment.VideoPagerFragmentCallbacks{

    ConversationFragment mConversationFragment;
    VideoPagerFragment mVideoPagerFragment;
    DisplayMetrics display;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpFragments();

    }


    protected void setUpFragments(){
        mConversationFragment = (ConversationFragment)
                getSupportFragmentManager().findFragmentById(R.id.conversation_fragment);

        mConversationFragment.setUp(this);

        mVideoPagerFragment = (VideoPagerFragment)
                getSupportFragmentManager().findFragmentById(R.id.video_pager_fragment);

        display = getResources().getDisplayMetrics();

        mConversationFragment.getView().getLayoutParams().height = 0;
        mVideoPagerFragment.getView().getLayoutParams().height = display.heightPixels;
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
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onResizeButtonClicked(Boolean isCollapseVideo) {
        if(isCollapseVideo){
        expandChat(mConversationFragment.getView());
        collapseVideo(mVideoPagerFragment.getView());
        }
        else {
            expandVideo(mVideoPagerFragment.getView());
            collapseChat(mConversationFragment.getView());
        }


    }

    public  void expandChat(final View v) {
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
        a.setDuration(500);
        v.startAnimation(a);
    }

    public  void collapseVideo(final View v) {

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
        a.setDuration(500);
        v.startAnimation(a);
    }

    public  void expandVideo(final View v) {
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        final float mToHeight = display.heightPixels;
        final float mFromHeight = display.heightPixels/2;;

        v.getLayoutParams().height = display.heightPixels;
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
        a.setDuration(500);
        v.startAnimation(a);
    }

    public  void collapseChat(final View v) {

        final float mToHeight = 0;
        final float mFromHeight = display.heightPixels/2;

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
        a.setDuration(500);
        v.startAnimation(a);
    }

}

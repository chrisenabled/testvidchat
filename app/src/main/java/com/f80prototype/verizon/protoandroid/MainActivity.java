package com.f80prototype.verizon.protoandroid;

import android.animation.ObjectAnimator;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.f80prototype.verizon.protoandroid.animations.ResizeVideoToSmallAnimation;
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

   /* public void createConversationFragment(View view) {

        if (findViewById(fragment_frame)!=null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            //if (savedInstanceState != null) {
            //   return;
            //}
            //Todo check saved instance somewhere and handle it accordingly
            mFragment = new ConversationFragment(mContext);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_frame, mFragment);
            transaction.setCustomAnimations(R.anim.slide_in_up,0);
            transaction.show(mFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        }
    }

    public static void changeViewPageParams(int i){
        if(i == 0)
        {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );

            layoutParams.height = VideoPagerFragment.resizeHeight;
            pager.setLayoutParams(layoutParams);
        }
        else
        {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT
            );
            pager.setLayoutParams(layoutParams);
        }

    }*/

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
    public void onResizeButtonClicked() {

        BounceInterpolator bounceInterpolator = new BounceInterpolator();
        ObjectAnimator anim = ObjectAnimator.ofFloat(mVideoPagerFragment.getView(),
                "translationY", 0,  - display.heightPixels/2);
        anim.setInterpolator(bounceInterpolator);
        anim.setDuration(1100).start();

        BounceInterpolator bounceInterpolator2 = new BounceInterpolator();
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(mConversationFragment.getView()
                , "translationY", 0f, - display.heightPixels/2);
        anim2.setInterpolator(bounceInterpolator2);
        anim2.setDuration(1100).start();


        //collapse(mVideoPagerFragment.getView());
        //expand(mConversationFragment.getView());
    }

    public  void expand(final View v) {
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int targetHeight = display.heightPixels/2;

        //v.getLayoutParams().height = display.heightPixels/2;
        //v.setVisibility(View.VISIBLE);
        final float mToHeight = display.heightPixels/2;
        final float mFromHeight = 0;
        v.getLayoutParams().height = 0;
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                float height = (mToHeight - mFromHeight) * interpolatedTime + mFromHeight;
                v.getLayoutParams().height = (int)height;
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

    public  void collapse(final View v) {
        final int targetHeight = display.heightPixels/3;

        final float mToHeight = display.heightPixels/3;
        final float mFromHeight = 0;

        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                float height = (mToHeight - mFromHeight) * interpolatedTime + mFromHeight;
                v.getLayoutParams().height = (int)height;
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

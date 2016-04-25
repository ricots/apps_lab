package com.lab.app_lab;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.os.Handler;

public class home extends Fragment {
    ViewFlipper flipper;
    Animation fade_in, fade_out;
    private float lastX;


    public home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_home,container,false);

        flipper = (ViewFlipper) v.findViewById(R.id.flipper1);
        flipper.startFlipping();

        return v;
    }

    /*public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {

            case MotionEvent.ACTION_DOWN:
                lastX = touchevent.getX();
                break;
            case MotionEvent.ACTION_UP:
                float currentX = touchevent.getX();

                // Handling left to right screen swap.
                if (lastX < currentX) {

                    // If there aren't any other children, just break.
                    if (flipper.getDisplayedChild() == 0)
                        break;

                    // Next screen comes in from left.
                    flipper.setInAnimation(getActivity(), R.anim.slide_in_from_left);
                    // Current screen goes out from right.
                    flipper.setOutAnimation(getActivity(), R.anim.slide_out_to_right);

                    // Display next screen.
                    flipper.showNext();
                }

                // Handling right to left screen swap.
                if (lastX > currentX) {

                    // If there is a child (to the left), kust break.
                    if (flipper.getDisplayedChild() == 1)
                        break;

                    // Next screen comes in from right.
                    flipper.setInAnimation(getActivity(), R.anim.slide_in_from_right);
                    // Current screen goes out from left.
                    flipper.setOutAnimation(getActivity(), R.anim.slide_out_to_left);

                    // Display previous screen.
                    flipper.showPrevious();
                }
                break;
        }
        return false;
}*/
}

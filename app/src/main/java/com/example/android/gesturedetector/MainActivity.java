package com.example.android.gesturedetector;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    View touchpad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        touchpad = findViewById(R.id.touchpad);
        touchpad.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = MotionEventCompat.getActionMasked(motionEvent);

                switch (action) {
                    case (MotionEvent.ACTION_DOWN):
                        Log.d(LOG_TAG, "Action on Touchpad was DOWN");
                    case (MotionEvent.ACTION_MOVE):
                        Log.d(LOG_TAG, "Action on Touchpad was MOVE");
                    case (MotionEvent.ACTION_UP):
                        Log.d(LOG_TAG, "Action on Touchpad was UP");
                    case (MotionEvent.ACTION_CANCEL):
                        Log.d(LOG_TAG, "Action on Touchpad was CANCEL");
                    case (MotionEvent.ACTION_OUTSIDE):
                        Log.d(LOG_TAG, "Movement occurred outside bounds on Touchpad " +
                                "of current screen element");
                }
                // if we return true, then that means thath we have handled the event (do not send it further)
                return false;
            }
        });

    }

    // This example shows an Activity, but you would use the same approach if
    // you were subclassing a View.
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = MotionEventCompat.getActionMasked(event);

        switch (action) {
            case (MotionEvent.ACTION_DOWN):
                Log.d(LOG_TAG, "Action was DOWN");
                return true;
            case (MotionEvent.ACTION_MOVE):
                Log.d(LOG_TAG, "Action was MOVE");
                return true;
            case (MotionEvent.ACTION_UP):
                Log.d(LOG_TAG, "Action was UP");
                return true;
            case (MotionEvent.ACTION_CANCEL):
                Log.d(LOG_TAG, "Action was CANCEL");
                return true;
            case (MotionEvent.ACTION_OUTSIDE):
                Log.d(LOG_TAG, "Movement occurred outside bounds " +
                        "of current screen element");
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }
}

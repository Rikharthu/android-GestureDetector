package com.example.android.gesturedetector;

import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    // NOTE:
    // Depending on what onTouchEvent() returns also influences which view
    // will handle additional events between DOWN and UP, such as MOVE
    // (also, the UP event will be executed there too)
    public static final boolean A_HANDLES_TOUCH = true;
    public static final boolean B_HANDLES_TOUCH = false;
    public static final boolean C_HANDLES_TOUCH = true;

    ViewGroup mViewGroupA;
    ViewGroup mViewGroupB;
    View mViewC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewGroupA = (ViewGroup) findViewById(R.id.viewgroup_a);
        mViewGroupB = (ViewGroup) findViewById(R.id.viewgroup_b);
        mViewC = findViewById(R.id.view_c);

        mViewGroupB.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                boolean result = B_HANDLES_TOUCH;

                logTouchEvent("B", event.getAction());
                if (result) {
                    // handling the event
                    Log.d("B", "result=" + result + ", touch event captured");
                } else {
                    // passing further
                    Log.d("B", "result=" + result + ", passing touch event further");
                }

                return result;
            }
        });

        mViewGroupA.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                boolean result = A_HANDLES_TOUCH;

                logTouchEvent("A", event.getAction());
                if (result) {
                    // handling the event
                    Log.d("A", "result=" + result + ", touch event captured");
                } else {
                    // passing further
                    Log.d("A", "result=" + result + ", passing touch event further");
                }

                return result;
            }
        });

        mViewC.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                boolean result = C_HANDLES_TOUCH;

                logTouchEvent("C", event.getAction());
                if (result) {
                    // handling the event
                    Log.d("C", "result=" + result + ", touch event captured");
                } else {
                    // passing further
                    Log.d("C", "result=" + result + ", passing touch event further");
                }

                return result;
            }
        });

        /*
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
    */
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_gesture_detector:
                launchActivity(GestureDetectoActivity.class);
                return true;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        Log.d(LOG_TAG,"onDestroy()");
        super.onDestroy();
    }

    private void launchActivity(Class activityClass) {
        Intent intent = new Intent(this, activityClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        // hide current
        menu.findItem(R.id.menu_item_touch_events).setVisible(false);
        return true;
    }

    // This example shows an Activity, but you would use the same approach if
    // you were subclassing a View.
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = MotionEventCompat.getActionMasked(event);

//        logTouchEvent("Activity", action);

        return true;
    }

    private void logTouchEvent(String tag, int eventCode) {
        switch (eventCode) {
            case (MotionEvent.ACTION_DOWN):
                Log.d(tag, "DOWN");
                break;
            case (MotionEvent.ACTION_MOVE):
                Log.d(tag, "MOVE");
                break;
            case (MotionEvent.ACTION_UP):
                Log.d(tag, "UP");
                break;
            case (MotionEvent.ACTION_CANCEL):
                Log.d(tag, "CANCEL");
                break;
            case (MotionEvent.ACTION_OUTSIDE):
                Log.d(tag, "Movement occurred outside bounds " +
                        "of current screen element");
        }
    }
}

package org.eclipse.paho.android.service.sample;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Nikhil on 23/09/2015.
 */
public class Notify {

    /**
     * Display a toast notification to the user
     * @param context Context from which to create a notification
     * @param text The text the toast should display
     * @param duration The amount of time for the toast to appear to the user
     */
    static void toast(Context context, CharSequence text, int duration) {
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}

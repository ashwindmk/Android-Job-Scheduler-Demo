package com.example.jobscheduler;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by ashwin on 24/10/17.
 */

public class MyJobExecutor extends AsyncTask<Void, Void, String> {

    private static final String TAG = MyJobExecutor.class.getSimpleName();

    @Override
    protected String doInBackground(Void... voids) {
        Log.w("debuglogging", TAG + " : doInBackground()");
        return "Long running background task finished...";
    }
}

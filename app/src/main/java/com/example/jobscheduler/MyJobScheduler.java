package com.example.jobscheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by ashwin on 24/10/17.
 */

public class MyJobScheduler extends JobService {

    private static final String TAG = MyJobScheduler.class.getSimpleName();
    private MyJobExecutor mMyJobExecutor;

    @Override
    public boolean onStartJob(final JobParameters jobParameters) {
        mMyJobExecutor = new MyJobExecutor() {
            @Override
            protected void onPostExecute(String s) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                Log.w("debuglogging", TAG + " : onStartJob() : " + s);
                jobFinished(jobParameters, true);  // True if want to reschedule the same job again
            }
        };

        mMyJobExecutor.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        mMyJobExecutor.cancel(true);
        // Return true to reschedule the job
        return false;
    }
}

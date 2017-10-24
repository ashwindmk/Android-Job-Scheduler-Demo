package com.example.jobscheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int JOB_ID = 101;
    private JobScheduler mJobScheduler;
    private JobInfo mJobInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ComponentName componentName = new ComponentName(this, MyJobScheduler.class);
        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, componentName);

        builder.setMinimumLatency(15000);  // This job is executed after 15 seconds
        builder.setOverrideDeadline(1000);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        builder.setPersisted(true);  // Job will continue after reboot

        mJobInfo = builder.build();
        mJobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
    }

    public void scheduleJob(View view) {
        mJobScheduler.schedule(mJobInfo);
        Toast.makeText(this, "Job scheduled", Toast.LENGTH_SHORT).show();
        Log.w("debuglogging", TAG + " : scheduleJob() : job scheduled");
    }

    public void clearJob(View view) {
        mJobScheduler.cancel(JOB_ID);
        Toast.makeText(this, "Job cancelled", Toast.LENGTH_SHORT).show();
        //mJobScheduler.cancelAll();
        Log.w("debuglogging", TAG + " : clearJob() : job cancelled");
    }

}

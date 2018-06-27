package com.carlosparra.githubjobs.githubjobsapi.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.carlosparra.githubjobs.githubjobsapi.R;
import com.carlosparra.githubjobs.githubjobsapi.models.Job;

public class JobDetailsActivity extends AppCompatActivity {

    private TextView jobTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);

        jobTitle = findViewById(R.id.tv_details_job_title);

        Job job = getIntent().getExtras().getParcelable("JOB");

        if (job != null) {
            jobTitle.setText(job.getTitle());
        }
    }
}

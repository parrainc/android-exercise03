package com.carlosparra.githubjobs.githubjobsapi.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.carlosparra.githubjobs.githubjobsapi.R;
import com.carlosparra.githubjobs.githubjobsapi.models.Job;

public class JobDetailsActivity extends AppCompatActivity {

    private ImageView jobCompanyLogo;
    private TextView jobTitle;
    private TextView jobLocation;
    private TextView jobPublishedDate;
    private TextView jobDescription;
    private AppCompatButton apply;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);

        jobCompanyLogo = findViewById(R.id.iv_details_company_logo);
        jobTitle = findViewById(R.id.tv_details_job_title);
        jobLocation = findViewById(R.id.tv_details_job_location);
        jobPublishedDate = findViewById(R.id.tv_details_published_date);
        jobDescription = findViewById(R.id.tv_details_job_description);
        apply = findViewById(R.id.btn_apply);

        Job job = getIntent().getExtras().getParcelable("JOB");

        if (job != null) {
            Glide.with(this)
                    .load(job.getCompany().getLogo())
                    .apply(new RequestOptions()
                            .placeholder(R.mipmap.image_no_available))
                    .into(jobCompanyLogo);

            setTitle(job.getTitle());
            jobTitle.setText(job.getTitle());
            jobLocation.setText(job.getLocation());
            jobPublishedDate.setText(job.getCreatedDate());
            jobDescription.setText(job.getDescription());
        }
    }
}

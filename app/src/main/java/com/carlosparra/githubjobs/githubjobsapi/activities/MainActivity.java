package com.carlosparra.githubjobs.githubjobsapi.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.carlosparra.githubjobs.githubjobsapi.R;
import com.carlosparra.githubjobs.githubjobsapi.adapters.JobCustomAdapter;
import com.carlosparra.githubjobs.githubjobsapi.models.Job;
import com.carlosparra.githubjobs.githubjobsapi.services.API;
import com.carlosparra.githubjobs.githubjobsapi.services.contracts.GitHubJobsService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private JobCustomAdapter adapter;
    private List<Job> jobsList;

    private ProgressBar loadingIndicator;
    private TextView loadingIndicatorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewJobsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadingIndicator = findViewById(R.id.pb_loading_jobs);
        loadingIndicatorText = findViewById(R.id.tv_loading_jobs);
        toggleLoaderIndicator(true);

        adapter = new JobCustomAdapter(new ArrayList<Job>(), this);
        adapter.setOnItemClickListener(new JobCustomAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(jobsList.get(position));
            }
        });
        recyclerView.setAdapter(adapter);

        getContentFromService();
    }

    private void getContentFromService() {
        GitHubJobsService service = API.getService().create(GitHubJobsService.class);
        Call<List<Job>> jobCall = service.getJobs("Android");

        jobCall.enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                Log.d(TAG, String.valueOf(response.code()));

                toggleLoaderIndicator(false);

                Toast.makeText(MainActivity.this,
                        "Service returned: " + response.body().size() + " jobs. With status " +
                                String.valueOf(response.code()),
                        Toast.LENGTH_SHORT).show();

                jobsList = response.body();
                adapter.updateDataSet(jobsList);
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                Log.e(TAG, t.getMessage());

                toggleLoaderIndicator(false);

                Toast.makeText(MainActivity.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    private void toggleLoaderIndicator(boolean isDisplay) {
        if (isDisplay) {
            loadingIndicator.setVisibility(View.VISIBLE);
            loadingIndicatorText.setVisibility(View.VISIBLE);
        }
        else {
            loadingIndicator.setVisibility(View.INVISIBLE);
            loadingIndicatorText.setVisibility(View.INVISIBLE);
        }
    }

    private void startActivity(Job job) {
        Intent intent = new Intent(this, JobDetailsActivity.class);
        intent.putExtra("JOB", job);
        startActivity(intent);
    }
}

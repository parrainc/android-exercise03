package com.carlosparra.githubjobs.githubjobsapi.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    private RecyclerView recyclerView;
    private JobCustomAdapter adapter;
    private List<Job> jobsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewJobsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getContentFromService();

        adapter = new JobCustomAdapter(new ArrayList<Job>(), this);
        recyclerView.setAdapter(adapter);
    }

    private void getContentFromService() {
        GitHubJobsService service = API.getService().create(GitHubJobsService.class);
        Call<List<Job>> jobCall = service.getJobs("Android");

        jobCall.enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                Log.d("SERVICE CALL", String.valueOf(response.code()));

                Toast.makeText(MainActivity.this,
                        "onResponse Successful: " + String.valueOf(response.code()),
                        Toast.LENGTH_SHORT).show();

                jobsList = response.body();
                adapter.updateDataSet(jobsList);
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                Log.e("ONFAILURE CALL", t.getMessage());

                Toast.makeText(MainActivity.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }
}

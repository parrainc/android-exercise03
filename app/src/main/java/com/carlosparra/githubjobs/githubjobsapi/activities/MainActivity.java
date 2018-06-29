package com.carlosparra.githubjobs.githubjobsapi.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
        recyclerView.addItemDecoration(
                new DividerItemDecoration(
                        this,
                        DividerItemDecoration.VERTICAL)
        );

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

        if (isNetworkAvailable()) {
            getContentFromService();
        } else {
            displayMessage("You must be connected to the internet", Toast.LENGTH_LONG);
            toggleLoaderIndicator(false);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_search) {
            
            Toast.makeText(this, "Search menu option was selected", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getContentFromService() {
        GitHubJobsService service = API.getService().create(GitHubJobsService.class);
        Call<List<Job>> jobCall = service.getJobs("Android");

        jobCall.enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                Log.d(TAG, String.valueOf(response.code()));

                toggleLoaderIndicator(false);

                displayMessage("Service returned: " + response.body().size() +
                        " jobs. With status " + String.valueOf(response.code()), Toast.LENGTH_SHORT);

                jobsList = response.body();
                adapter.updateDataSet(jobsList);
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                Log.e(TAG, t.getMessage());

                toggleLoaderIndicator(false);

                displayMessage(t.getMessage(), Toast.LENGTH_SHORT);
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

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager != null ?
                connectivityManager.getActiveNetworkInfo() : null;

        return (networkInfo != null && networkInfo.isConnectedOrConnecting());
    }

    private void displayMessage(String message, int duration) {
        Toast.makeText(this, message, duration).show();
    }
}

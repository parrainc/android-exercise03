package com.carlosparra.githubjobs.githubjobsapi.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.carlosparra.githubjobs.githubjobsapi.R;
import com.carlosparra.githubjobs.githubjobsapi.adapters.JobCustomAdapter;
import com.carlosparra.githubjobs.githubjobsapi.models.Job;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private JobCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewJobsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new JobCustomAdapter(new ArrayList<Job>(), this);
        recyclerView.setAdapter(adapter);
    }
}

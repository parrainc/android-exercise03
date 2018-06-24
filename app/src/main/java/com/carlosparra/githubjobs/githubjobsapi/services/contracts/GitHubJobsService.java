package com.carlosparra.githubjobs.githubjobsapi.services.contracts;

import com.carlosparra.githubjobs.githubjobsapi.models.Job;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GitHubJobsService {

    @GET("/")
    Call<List<Job>> getJobs(@Query("description") String description);

    @GET("/")
    Call<List<Job>> getJobs(@Query("description") String description,
                            @Query("location") String location);
}

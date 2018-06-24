package com.carlosparra.githubjobs.githubjobsapi.services;

import retrofit2.Retrofit;

public class API {
    public static final String BASE_URL = "https://jobs.github.com/positions.json";

    public static Retrofit getService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();

        return retrofit;
    }

}

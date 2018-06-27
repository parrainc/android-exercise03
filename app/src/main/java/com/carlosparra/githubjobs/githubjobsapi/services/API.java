package com.carlosparra.githubjobs.githubjobsapi.services;

import com.carlosparra.githubjobs.githubjobsapi.models.Job;
import com.carlosparra.githubjobs.githubjobsapi.services.deserializers.JobDeserializer;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {
    public static final String BASE_URL = "https://jobs.github.com/";
    private static Retrofit retrofit = null;

    public static Retrofit getService() {
        if (retrofit == null) {

            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Job.class, new JobDeserializer());

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(builder.create()))
                    .build();
        }

        return retrofit;
    }

}

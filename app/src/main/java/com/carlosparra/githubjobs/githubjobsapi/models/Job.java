package com.carlosparra.githubjobs.githubjobsapi.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class Job {

    @SerializedName("id")
    private String id;
    @SerializedName("created_at")
    private String createdDate;
    @SerializedName("title")
    private String title;
    @SerializedName("location")
    private String location;
    @SerializedName("description")
    private String description;
    @SerializedName("how_to_apply")
    private String howToApply;
    private Company x_company;
    @SerializedName("url")
    private String url;

    public Job(String id, String createdDate, String title, String location,
               String description, String howToApply, String url) {
        this.id = id;
        this.createdDate = createdDate;
        this.title = title;
        this.location = location;
        this.description = description;
        this.howToApply = howToApply;
        //this.x_company = x_company;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHowToApply() {
        return howToApply;
    }

    public void setHowToApply(String howToApply) {
        this.howToApply = howToApply;
    }

    public Company getX_company() {
        return x_company;
    }

    public void setX_company(Company x_company) {
        this.x_company = x_company;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static Company parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(response, Company.class);
    }
}

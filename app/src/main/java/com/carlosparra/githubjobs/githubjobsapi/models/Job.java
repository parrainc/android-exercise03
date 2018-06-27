package com.carlosparra.githubjobs.githubjobsapi.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class Job implements Parcelable {

    private String id;
    private String createdDate;
    private String title;
    private String location;
    private String description;
    private String howToApply;
    private Company company;
    private String url;

    public Job(String id, String createdDate, String title, String location,
               String description, String howToApply, String url, Company company) {
        this.id = id;
        this.createdDate = createdDate;
        this.title = title;
        this.location = location;
        this.description = description;
        this.howToApply = howToApply;
        this.company = company;
        this.url = url;
    }

    protected Job(Parcel in) {
        id = in.readString();
        createdDate = in.readString();
        title = in.readString();
        location = in.readString();
        description = in.readString();
        howToApply = in.readString();
        url = in.readString();
        company = in.readParcelable(getClass().getClassLoader());
    }

    public static final Creator<Job> CREATOR = new Creator<Job>() {
        @Override
        public Job createFromParcel(Parcel in) {
            return new Job(in);
        }

        @Override
        public Job[] newArray(int size) {
            return new Job[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(id);
        parcel.writeString(createdDate);
        parcel.writeString(title);
        parcel.writeString(location);
        parcel.writeString(description);
        parcel.writeString(howToApply);
        parcel.writeString(url);
        parcel.writeParcelable(company, flags);
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

    public Company get_company() {
        return company;
    }

    public void set_company(Company company) {
        this.company = company;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

//    public static Company parseJSON(String response) {
//        Gson gson = new GsonBuilder().create();
//        return gson.fromJson(response, Company.class);
//    }
}

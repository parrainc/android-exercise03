package com.carlosparra.githubjobs.githubjobsapi.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Company implements Parcelable {
    private String name;
    private String logo;
    private String url;

    public Company(String name, String logo, String url) {
        this.name = name;
        this.logo = logo;
        this.url = url;
    }

    protected Company(Parcel in) {
        name = in.readString();
        logo = in.readString();
        url = in.readString();
    }

    public static final Creator<Company> CREATOR = new Creator<Company>() {
        @Override
        public Company createFromParcel(Parcel in) {
            return new Company(in);
        }

        @Override
        public Company[] newArray(int size) {
            return new Company[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(logo);
        parcel.writeString(url);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

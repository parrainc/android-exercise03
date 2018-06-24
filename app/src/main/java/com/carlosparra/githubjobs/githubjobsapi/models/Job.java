package com.carlosparra.githubjobs.githubjobsapi.models;

import java.util.Date;

public class Job {

    private String id;
    private Date createdDate;
    private String title;
    private String location;
    private String description;
    private String howToApply;
    private Company company;
    private String url;

    public Job(String id, Date createdDate, String title, String location,
               String description, String howToApply, Company company, String url) {
        this.id = id;
        this.createdDate = createdDate;
        this.title = title;
        this.location = location;
        this.description = description;
        this.howToApply = howToApply;
        this.company = company;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

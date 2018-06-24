package com.carlosparra.githubjobs.githubjobsapi.models;

public class Company {
    private String name;
    private String logo;
    private String url;

    public Company(String name, String logo, String url) {
        this.name = name;
        this.logo = logo;
        this.url = url;
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

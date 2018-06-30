package com.carlosparra.githubjobs.githubjobsapi.services.deserializers;

import android.os.Build;
import android.text.Html;

import com.carlosparra.githubjobs.githubjobsapi.models.Company;
import com.carlosparra.githubjobs.githubjobsapi.models.Job;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JobDeserializer implements JsonDeserializer<Job> {
    @Override
    public Job deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        String id = json.getAsJsonObject().get("id").getAsString();
        String createdDate = json.getAsJsonObject().get("created_at").getAsString();
        String title = json.getAsJsonObject().get("title").getAsString();
        String location = json.getAsJsonObject().get("location").getAsString();
        String description = json.getAsJsonObject().get("description").getAsString();
        String howToApply = json.getAsJsonObject().get("how_to_apply").getAsString();
        String url = json.getAsJsonObject().get("url").getAsString();

        String companyName = json.getAsJsonObject().get("company").getAsString();
        String companyUrl = "";
        String companyLogo = "";

        if (!json.getAsJsonObject().get("company_url").isJsonNull())
            companyUrl = json.getAsJsonObject().get("company_url").getAsString();

        if (!json.getAsJsonObject().get("company_logo").isJsonNull())
            companyLogo = json.getAsJsonObject().get("company_logo").getAsString();

        Company company = new Company(companyName, companyLogo, companyUrl);

        SimpleDateFormat df = new SimpleDateFormat("E MMM dd hh:mm:ss z yyyy");
        try {
            Date date = df.parse(createdDate);
            createdDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            description = String.valueOf(Html.fromHtml(description, Html.FROM_HTML_MODE_LEGACY));
        }else {
            description = String.valueOf(Html.fromHtml(description));
        }

        return new Job(id, createdDate, title, location, description, howToApply, url, company);
    }
}

package com.vacflix.backend.vacflixbackend.services.search;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.vacflix.backend.vacflixbackend.auth.ApiAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;

//service that acts as a proxy for searching queries
@Service
public class SearchService {

    @Autowired
    private Environment env;

    //Youtube type because it's youtube builder from ApiAuth
    public YouTube getAuth() {
        ApiAuth auth = new ApiAuth(env);
        return auth.getAuth();
    }

    //TODO: make a JSON?
    public String getVideosFromQuery(String query) throws IOException {
        ApiAuth auth = new ApiAuth(env);
        YouTube youTube = auth.getAuth();

        // Define and execute the API request
        //list req?
        YouTube.Search.List request = youTube.search()
                .list(Collections.singletonList("snippet"));
        //https://developers.google.com/resources/api-libraries/documentation/youtube/v3/java/latest/com/google/api/services/youtube/model/SearchListResponse.html
        //https://googleapis.github.io/google-http-java-client/json.html
        SearchListResponse response = request.setMaxResults(25L)
                .setQ(query)
                .execute();
        //console log
        System.out.println("from Search Service ---");
        System.out.println(response.toString());
        //TODO: send back a JSON?
        return response.toString();
    }

    //JSON works
    public SearchListResponse getTestQuery(String query) throws IOException {
        //TODO: FIX
        ApiAuth auth = new ApiAuth(env);
        YouTube youtubeService = auth.getAuth();
        //I love outdated docs
        YouTube.Search.List request = youtubeService.search()
                .list(Collections.singletonList("snippet"));
        SearchListResponse response = request
                .setQ(query)
                .execute();
        System.out.println(response);
        //type is class com.google.api.services.youtube.model.SearchListResponse
        System.out.println("type is "+response.getClass());
        //it's actually from
        //https://www.javadoc.io/doc/com.google.http-client/google-http-client/1.40.1/com/google/api/client/json/GenericJson.html
        return response;
    }

}

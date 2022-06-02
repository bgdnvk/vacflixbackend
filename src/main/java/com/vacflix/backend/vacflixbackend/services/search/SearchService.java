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

    //TODO: make a JSON?
    public String getVideosFromQuery(String query) throws IOException {
        ApiAuth auth = new ApiAuth(env);
        YouTube youTube = auth.getYoutube();

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
}

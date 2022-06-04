package com.vacflix.backend.vacflixbackend.services.search;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.vacflix.backend.vacflixbackend.auth.ApiAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;

@Service
public class SearchServiceTesting {

    @Autowired
    private Environment env;

    //Youtube type because it's youtube builder from ApiAuth
    public YouTube getAuth() {
        ApiAuth auth = new ApiAuth(env);
        return auth.getAuth();
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

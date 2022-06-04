package com.vacflix.backend.vacflixbackend.services.search;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
import com.vacflix.backend.vacflixbackend.auth.ApiAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

//service that acts as a proxy for searching queries
@Service
public class SearchService {

    @Autowired
    private Environment env;

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

    //TODO: make it a JSON
    //assign the json response and return it here
    String stringResponse;

    //TODO: returns a string
    //get a single video from passing the ID through a query
    public String getVideoFromId(String id){
        try{
            ApiAuth service = new ApiAuth(env);
            YouTube youtube = service.getAuth();

            YouTube.Videos.List request = youtube.videos()
                    .list(Collections.singletonList("snippet,contentDetails,statistics"));
            VideoListResponse response = request.setId(Collections.singletonList(id)).execute();
            System.out.println(response);

            //trying to get searchResult
            List<Video> results = response.getItems();
            System.out.println("------------INSIDE GETVIDEO-------------");
            System.out.println("list of results");
            System.out.println(results);

            stringResponse = response.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringResponse;
    }

}

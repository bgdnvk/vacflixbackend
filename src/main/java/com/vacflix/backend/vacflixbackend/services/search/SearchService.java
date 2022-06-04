package com.vacflix.backend.vacflixbackend.services.search;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
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

    //looks like it stops at 5 videos
    public String getVideosFromPlaylistId(String id) {

        //TODO: refactor auth serv
        //get the authorization through the api
        try{
            ApiAuth service = new ApiAuth(env);
            YouTube youtube = service.getAuth();

            YouTube.PlaylistItems.List request = youtube.playlistItems()
                    .list(Collections.singletonList("contentDetails"));
//            PlaylistItemListResponse response = request.setId(Collections.singletonList(id)).execute();

            PlaylistItemListResponse response = request
                    .setPlaylistId(id)
                    .execute();
            //TODO: check
            //from official docs
//            YouTube youtubeService = getService();
//            // Define and execute the API request
//            YouTube.PlaylistItems.List request = youtubeService.playlistItems()
//                    .list("snippet");
//            PlaylistItemListResponse response = request.setKey(DEVELOPER_KEY)
//                    .setPlaylistId("PL0vfts4VzfNigohKr5sPrkcPFpuZmTe2C")
//                    .execute();
//            System.out.println(response);

            System.out.println(response);

            System.out.println(response);
            stringResponse = response.toString();

            //PL7jsIYDyTYItwUnGLWLYPGsPScPetNT3t
        } catch (
                GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }


        return stringResponse;
    }

    //CHANNEL PROXY
    public String getVideosFromChannelId(String id){
        try {
            ApiAuth service = new ApiAuth(env);
            YouTube youtube = service.getAuth();

            //methods to get the playlists
            YouTube.Playlists.List request = youtube.playlists()
                    .list(Collections.singletonList("snippet,contentDetails"));
            PlaylistListResponse response = request.setChannelId(id)
                    .setMaxResults(25L)
                    .execute();
            System.out.println(response);
            System.out.println(response.getClass());
            //return the playlist
            //TODO: check json here
            stringResponse = response.toString();

        } catch (
                GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
        //TODO: make a proper JSON
        return stringResponse;
    }

}

package com.vacflix.backend.vacflixbackend.api;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeRequestInitializer;
import com.google.api.services.youtube.model.PlaylistListResponse;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.vacflix.backend.vacflixbackend.service.YouTubeApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

@RestController
public class YouTubeController {
    @Autowired
    YouTubeApiService youTubeApiService;

    //TODO: save
    //use the playlist ID in the call to SAVE the playlist to the db
    @GetMapping(path = "/crawl/playlist/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String playlist(@PathVariable String id){
        //UCwoAou1VZfbYfz-TysRzDCA
        return youTubeApiService.getYoutubePlaylist(id);
    }

    //TODO: save
    //use the playlist ID in the call to SAVE the playlist videos to the db
    @GetMapping(path = "/crawl/playlist/items/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String playListItems(@PathVariable String id){
        return youTubeApiService.getVideosFromPlaylist(id);
    }

    //getters

    //use the playlist ID in the call to get the json of the playlist from our db
    @GetMapping(path = "/playlist/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPlayList(@PathVariable String id){
        //UCwoAou1VZfbYfz-TysRzDCA
        return youTubeApiService.getYoutubePlaylist(id);
    }

    //use the playlist ID to get the json items from the playlist in our db
    @GetMapping(path = "/playlist/items/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPlayListItems(@PathVariable String id){
        return youTubeApiService.getVideosFromPlaylist(id);
    }

    //add single video id, mostly for testing purposes
    @GetMapping(path = "/video/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getVideoById(@PathVariable String id){
        return youTubeApiService.getVideo(id);
    }

    //search query
    //TODO: handle exceptions better
    @GetMapping(path = "/search/{query}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getSearchQuery(@PathVariable String query) throws IOException {
        return youTubeApiService.getVideosFromSearch(query);
    }


}

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

    //use the playlist ID in the call to get the json from youtube
    @GetMapping(path = "/playlist/{id}")
    public String playlist(@PathVariable String id){
        //UCwoAou1VZfbYfz-TysRzDCA
        return youTubeApiService.getYoutubePlaylist(id);
    }

    //use the playlist ID to get the items from the playlist
    @GetMapping(path = "/playlist/items/{id}")
    public String playListItems(@PathVariable String id){
        return youTubeApiService.getVideosFromPlaylist(id);
    }


}

package com.vacflix.backend.vacflixbackend.api;

import com.vacflix.backend.vacflixbackend.services.apiService.YouTubeApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class SearchController {

    //TODO: make a new service for search
    @Autowired
    YouTubeApiService youTubeApiService;

    //returns a list of videos that are inside items arr
    @GetMapping(path = "/search/{query}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getVideosForQuery(@PathVariable String query) throws IOException {
        return youTubeApiService.getVideosFromSearch(query);
    }
}

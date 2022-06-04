package com.vacflix.backend.vacflixbackend.api.search;

import com.google.api.services.youtube.model.SearchListResponse;
import com.vacflix.backend.vacflixbackend.services.apiService.YouTubeApiService;
import com.vacflix.backend.vacflixbackend.services.search.SearchService;
import com.vacflix.backend.vacflixbackend.services.search.SearchServiceTesting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

//Controller acts as a proxy to YouTube
//every search/ endpoint calls the YouTube's API and gets the data back
@RestController
public class SearchQueryController {

    @Autowired
    SearchService searchService;
    //TODO: clean b4 pushing to prod
    //random test service for dev
    @Autowired
    SearchServiceTesting searchServiceTesting;

    //testing
    //example: http://localhost:8081/search/bad
    //get 25 videos from the youtube api
    //services/searchService
    @GetMapping(path = "/search/query/{query}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getVideosForQuery(@PathVariable String query) throws IOException {
        return searchService.getVideosFromQuery(query);
    }

    //TODO: clean the test
    //TESTING SearchService.getTestQuery method
    //official API docs json example
    @GetMapping(path = "/test/search/{query}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SearchListResponse getVideosSearchTest(@PathVariable String query) throws IOException {
        return searchServiceTesting.getTestQuery(query);
    }

}

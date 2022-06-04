package com.vacflix.backend.vacflixbackend.api;

import com.vacflix.backend.vacflixbackend.services.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchIdController {

    @Autowired
    SearchService searchService;

    //proxy search for a single ID
    @GetMapping(path = "/search/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getVideoById(@PathVariable String id){
        return searchService.getVideoFromId(id);
    }

}

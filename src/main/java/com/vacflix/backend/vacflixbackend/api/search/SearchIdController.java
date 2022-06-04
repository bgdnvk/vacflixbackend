package com.vacflix.backend.vacflixbackend.api.search;

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
    //http://localhost:8081/search/id/sN9jC-_j3zc
    @GetMapping(path = "/search/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getVideoById(@PathVariable String id){
        return searchService.getVideoFromId(id);
    }

    //get 5 video IDs from the list
    //http://localhost:8081/search/playlist/items/id/RDGl0IPvyYZnY
    @GetMapping(path = "/search/playlist/items/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPlayListItems(@PathVariable String id){
        return searchService.getVideosFromPlaylistId(id);
    }


    //videos from a channel
    //http://localhost:8081/search/channel/id/UC_x5XG1OV2P6uZZ5FSM9Ttw
    //https://stackoverflow.com/questions/14366648/how-can-i-get-a-channel-id-from-youtube
    @GetMapping(path = "/search/channel/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPlayList(@PathVariable String id){
        //UCwoAou1VZfbYfz-TysRzDCA
        return searchService.getVideosFromChannelId(id);
    }


}

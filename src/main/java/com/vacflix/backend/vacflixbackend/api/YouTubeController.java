package com.vacflix.backend.vacflixbackend.api;

import com.vacflix.backend.vacflixbackend.services.crawler.impl.ApiCrawlerServiceImpl;
import com.vacflix.backend.vacflixbackend.services.searcher.YouTubeApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class YouTubeController {
    //most of the methods are here
    @Autowired
    YouTubeApiService youTubeApiService;

    //get the crawler bean
    @Autowired
    ApiCrawlerServiceImpl pageCrawler;

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

    // TODO: refactor the crawler
    //first crawl test into db
    //loads into crawling_info db
    @GetMapping(value = "crawl/{keyword}/{pageToCrawl}")
    public String crawlVideo(@PathVariable String keyword, @PathVariable long pageToCrawl) {
        return pageCrawler.crawlYoutubeVideoInfo(keyword,pageToCrawl);
    }


}

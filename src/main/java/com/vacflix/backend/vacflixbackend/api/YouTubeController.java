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

//    @Autowired
//    private Environment env;
//
//    private static final long NUMBER_OF_VIDEOS_RETURNED = 50;
//
//    public static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
//
//    public static final JsonFactory JSON_FACTORY = new JacksonFactory();
//
//    private YouTube youtube;
//
//    private long count = 0;
//
//    String playListJSON;
//
//    public String getYoutubePlaylist(String id){
//        try {
//
//            youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
//                public void initialize(HttpRequest request) throws IOException {
//                }
//            }).setApplicationName("YoutubeVideoInfo")
//                    .setYouTubeRequestInitializer(new YouTubeRequestInitializer(env.getProperty("youtube.apikey"))).build();
//
////            YouTube.Search.List search = youtube.search().list(Collections.singletonList("id,snippet"));
////
////
////            search.setQ(queryTerm);
////            search.setType(Collections.singletonList("video"));
////            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
//            YouTube.Playlists.List request = youtube.playlists()
//                    .list(Collections.singletonList("snippet,contentDetails"));
//            PlaylistListResponse response = request.setChannelId(id)
//                    .setMaxResults(25L)
//                    .execute();
//            System.out.println(response);
//
//            playListJSON = response.toString();
//
//            //UC_x5XG1OV2P6uZZ5FSM9Ttw
//
//        } catch (
//                GoogleJsonResponseException e) {
//            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
//                    + e.getDetails().getMessage());
//        } catch (IOException e) {
//            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }
//        //TODO: make a proper JSON
//        return playListJSON;
//    }
//    @GetMapping(path = "/youtube/playlist/{id}")
//    public void showPlaylist(@PathVariable String id){
//        getYoutubePlaylist("UC_x5XG1OV2P6uZZ5FSM9Ttw");
//    }
    @GetMapping(path = "/channel/playlist/{id}")
    public String playlist(@PathVariable String id){
        //UCwoAou1VZfbYfz-TysRzDCA
        return youTubeApiService.getYoutubePlaylist(id);
    }


}

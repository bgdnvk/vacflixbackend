package com.vacflix.backend.vacflixbackend.service;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeRequestInitializer;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import com.google.api.services.youtube.model.PlaylistListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;

@Service
public class YouTubeApiService {
    @Autowired
    private Environment env;

    private static final long NUMBER_OF_VIDEOS_RETURNED = 50;

    public static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    public static final JsonFactory JSON_FACTORY = new JacksonFactory();

    private long count = 0;

    String playListJSON;

    //build the youtube auth from the api key
    public String getYoutubePlaylist(String id){
        try {
            ApiAuth service = new ApiAuth(env);
            YouTube youtube = service.getYoutube();

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
            playListJSON = response.toString();

            //junta andalucia canal
            //UC_x5XG1OV2P6uZZ5FSM9Ttw

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
        return playListJSON;
    }

    public String getVideosFromPlaylist(String id) {

        //TODO: refactor auth serv
        //get the authorization through the api
        try{
            ApiAuth service = new ApiAuth(env);
            YouTube youtube = service.getYoutube();

            YouTube.PlaylistItems.List request = youtube.playlistItems()
                    .list(Collections.singletonList("contentDetails"));
            PlaylistItemListResponse response = request.setId(Collections.singletonList(id)).execute();
            System.out.println(response);
            playListJSON = response.toString();

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


        return playListJSON;
    }
}

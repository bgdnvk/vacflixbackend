package com.vacflix.backend.vacflixbackend.service;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import com.vacflix.backend.vacflixbackend.entity.YouTubeVideoInfo;
import com.vacflix.backend.vacflixbackend.entity.YoutubeChannelInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class YouTubeApiService {
    //TODO: check youtube service
    //from official google docs finally
//    // You need to set this value for your code to compile.
//    // For example: ... DEVELOPER_KEY = "YOUR ACTUAL KEY";
//    private static final String DEVELOPER_KEY = "YOUR_API_KEY";
//
//    private static final String APPLICATION_NAME = "API code samples";
//    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
//
//    /**
//     * Build and return an authorized API client service.
//     *
//     * @return an authorized API client service
//     * @throws GeneralSecurityException, IOException
//     */
//    public static YouTube getService() throws GeneralSecurityException, IOException {
//        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
//        return new YouTube.Builder(httpTransport, JSON_FACTORY, null)
//                .setApplicationName(APPLICATION_NAME)
//                .build();
//    }

    @Autowired
    private Environment env;

    private static final long NUMBER_OF_VIDEOS_RETURNED = 50;

    public static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    public static final JsonFactory JSON_FACTORY = new JacksonFactory();

    //BEANS/services
    @Autowired
    IYoutubeVideoInfoService IYoutubeVideoInfoService;

    @Autowired
    IYoutubeChannelService youtubeChannelService;


    //assign the json response and return it here
    String stringResponse;

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
            stringResponse = response.toString();

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
        return stringResponse;
    }

    public String getVideosFromPlaylist(String id) {

        //TODO: refactor auth serv
        //get the authorization through the api
        try{
            ApiAuth service = new ApiAuth(env);
            YouTube youtube = service.getYoutube();

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

    //get a single video from passing the ID through a query
    public String getVideo(String id){
        try{
            ApiAuth service = new ApiAuth(env);
            YouTube youtube = service.getYoutube();

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

    //TODO: make proper exceptions
    //return a string with the search result
    //inside the items array you have all the videos
    public String getVideosFromSearch(String query) throws IOException {

        ApiAuth service = new ApiAuth(env);
        YouTube youtube = service.getYoutube();

        // Define and execute the API request
        YouTube.Search.List request = youtube.search()
                .list(Collections.singletonList("snippet"));
        SearchListResponse response = request.setMaxResults(25L)
                .setQ(query)
                .execute();
        //console log
        System.out.println(response.toString());
        return response.toString();
    }

    //save your video to the db
    //TODO: only for lists, not single video. Need to fix
    private void saveVideo(SearchResult searchResult) throws IOException {

        SearchResult singleVideo = searchResult;
        YouTubeVideoInfo youTubeVideoInfo = IYoutubeVideoInfoService.getByVideoId(singleVideo.getId().getVideoId());

        if (youTubeVideoInfo == null) {
            youTubeVideoInfo = new YouTubeVideoInfo();
            ResourceId rId = singleVideo.getId();
            //TODO: query could be useful for global search
//            youTubeVideoInfo.setKeyword(query);
            youTubeVideoInfo.setDescription(singleVideo.getSnippet().getDescription());
            youTubeVideoInfo.setPublishedDate(new Date(singleVideo.getSnippet().getPublishedAt().getValue()));

            if (rId.getKind().equals("youtube#video")) {
                Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();
                youTubeVideoInfo.setVideoId(rId.getVideoId());
                youTubeVideoInfo.setTitle(singleVideo.getSnippet().getTitle());
                youTubeVideoInfo.setThumbnailUrl(thumbnail.getUrl());

                youTubeVideoInfo.setChannelInfo(getChannelDetailsById(singleVideo.getSnippet().getChannelId()));
// TODO: missing method
//                youTubeVideoInfo.setVideoStatistics(getVideosStatistics(rId.getVideoId()));
            }

            IYoutubeVideoInfoService.save(youTubeVideoInfo);
        } else {
            System.out.println("dupe");
        }
    }



    private YoutubeChannelInfo getChannelDetailsById(String channelId) throws IOException {
        //TODO: refactor service
        ApiAuth service = new ApiAuth(env);
        YouTube youtube = service.getYoutube();

        YouTube.Channels.List channels = youtube.channels().list(Collections.singletonList("snippet, statistics"));

        YoutubeChannelInfo youtubeChannelInfo = new YoutubeChannelInfo();
        youtubeChannelInfo.setChannelId(channelId);
        channels.setId(Collections.singletonList(channelId));
        ChannelListResponse channelResponse = channels.execute();
        Channel c = channelResponse.getItems().get(0);

        youtubeChannelInfo.setName(c.getSnippet().getTitle());
        youtubeChannelInfo.setSubscriptionCount(c.getStatistics().getSubscriberCount().longValue());

        YoutubeChannelInfo channelInfo = youtubeChannelService.getByChannelId(channelId);

        if (channelInfo == null) {
            youtubeChannelService.save(youtubeChannelInfo);
        } else {
            return channelInfo;
        }
        return youtubeChannelInfo;
    }
}

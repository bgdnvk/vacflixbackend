package com.vacflix.backend.vacflixbackend.services.crawler.impl;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeRequestInitializer;
import com.google.api.services.youtube.model.*;
import com.vacflix.backend.vacflixbackend.entity.CrawlingInfo;
import com.vacflix.backend.vacflixbackend.entity.YouTubeVideoInfo;
import com.vacflix.backend.vacflixbackend.entity.YoutubeChannelInfo;
import com.vacflix.backend.vacflixbackend.entity.YoutubeVideoStatistics;
import com.vacflix.backend.vacflixbackend.services.access.ICrawlingInfoService;
import com.vacflix.backend.vacflixbackend.services.access.IYoutubeChannelService;
import com.vacflix.backend.vacflixbackend.services.access.IYoutubeVideoStatService;
import com.vacflix.backend.vacflixbackend.services.crawler.IApiCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class ApiCrawlerServiceImpl implements IApiCrawler {
    @Autowired
    private Environment env;

    private static final long NUMBER_OF_VIDEOS_RETURNED = 50;

    public static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    public static final JsonFactory JSON_FACTORY = new JacksonFactory();

    private YouTube youtube;

    private long count = 0;

    @Autowired
    com.vacflix.backend.vacflixbackend.services.access.IYoutubeVideoInfoService IYoutubeVideoInfoService;

    @Autowired
    IYoutubeVideoStatService youtubeVideoStatService;

    @Autowired
    IYoutubeChannelService youtubeChannelService;

    @Autowired
    ICrawlingInfoService crawlingInfoService;

    //crawlVideo in Controller
    @Override
    @Async("threadPoolTaskExecutor")
    public String crawlYoutubeVideoInfo(String keyword,long pageToCrawl) {
        getYoutubeVideoList(keyword,pageToCrawl);
        return "loading..";
    }
    //loads data into crawling_info
    //crawlVideo in Controller
    @Transactional
    public List<Object> getYoutubeVideoList(String queryTerm, long pageToCrawl) {

        try {

            youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("YoutubeVideoInfo")
                    .setYouTubeRequestInitializer(new YouTubeRequestInitializer(env.getProperty("youtube.apikey"))).build();

            YouTube.Search.List search = youtube.search().list(Collections.singletonList("id,snippet"));


            search.setQ(queryTerm);
            search.setType(Collections.singletonList("video"));
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

            for (int i = 0; i < pageToCrawl; i++) {
                String pageToken = null;
                CrawlingInfo crawlingInfo = crawlingInfoService.getBySearchKey(queryTerm);
                if (crawlingInfo != null && crawlingInfo.getNextPageToken() != null) {
                    pageToken = crawlingInfo.getNextPageToken();
                    count = crawlingInfo.getTotalCount();
                    crawlingInfo.setCurrentPageToken(pageToken);
                } else if (crawlingInfo == null) {
                    crawlingInfo = new CrawlingInfo();
                    count = 0;
                    crawlingInfo.setSearchKey(queryTerm);
                    crawlingInfo.setCurrentPageToken(null);
                }

                if (pageToken != null) {
                    search.setPageToken(pageToken);
                }


                SearchListResponse searchResponse = search.execute();

                List<SearchResult> searchResultList = searchResponse.getItems();
                if (searchResultList != null) {
                    extractAndSave(searchResultList.iterator(), queryTerm);
                }

                crawlingInfo.setNextPageToken(searchResponse.getNextPageToken());

                crawlingInfoService.update(crawlingInfo);

                System.out.println("Next Page token : " + searchResponse.getNextPageToken());
            }
        } catch (GoogleJsonResponseException e) {
            System.err.println("service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }
    private void extractAndSave(Iterator<SearchResult> iteratorSearchResults, String query) throws IOException {

        if (!iteratorSearchResults.hasNext()) {
            System.out.println("no results");
        }

        while (iteratorSearchResults.hasNext()) {

            SearchResult singleVideo = iteratorSearchResults.next();

            System.out.println("vid num = " + count + " inserting video info " + singleVideo.getId().getVideoId());
            count++;
            YouTubeVideoInfo youTubeVideoInfo = IYoutubeVideoInfoService.getByVideoId(singleVideo.getId().getVideoId());

            if (youTubeVideoInfo == null) {
                youTubeVideoInfo = new YouTubeVideoInfo();
                ResourceId rId = singleVideo.getId();

                youTubeVideoInfo.setKeyword(query);
                youTubeVideoInfo.setDescription(singleVideo.getSnippet().getDescription());
                youTubeVideoInfo.setPublishedDate(new Date(singleVideo.getSnippet().getPublishedAt().getValue()));

                if (rId.getKind().equals("youtube#video")) {
                    Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();
                    youTubeVideoInfo.setVideoId(rId.getVideoId());
                    youTubeVideoInfo.setTitle(singleVideo.getSnippet().getTitle());
                    youTubeVideoInfo.setThumbnailUrl(thumbnail.getUrl());

                    youTubeVideoInfo.setChannelInfo(getChannelDetailsById(singleVideo.getSnippet().getChannelId()));

                    youTubeVideoInfo.setVideoStatistics(getVideosStatistics(rId.getVideoId()));
                }

                IYoutubeVideoInfoService.save(youTubeVideoInfo);
            } else {
                System.out.println("dupe ");
            }


        }
    }

    private YoutubeChannelInfo getChannelDetailsById(String channelId) throws IOException {
        // TODO: lol DRY
        YouTube.Channels.List channels = youtube.channels().list(Collections.singletonList("snippet, statistics"));

        YoutubeChannelInfo youtubeChannelInfo = new YoutubeChannelInfo();
        youtubeChannelInfo.setChannelId(channelId);
        channels.setId(Collections.singletonList(channelId));
        ChannelListResponse channelResponse = channels.execute();
        Channel c = channelResponse.getItems().get(0);

        youtubeChannelInfo.setName(c.getSnippet().getTitle());

        //check for null
        try{
            youtubeChannelInfo.setSubscriptionCount(c.getStatistics().getSubscriberCount().longValue());
        } catch (Exception e){
            System.out.println(e);
        }


        YoutubeChannelInfo channelInfo = youtubeChannelService.getByChannelId(channelId);

        if (channelInfo == null) {
            youtubeChannelService.save(youtubeChannelInfo);
        } else {
            return channelInfo;
        }
        return youtubeChannelInfo;
    }

    public YoutubeVideoStatistics getVideosStatistics(String id) throws IOException {
        YouTube.Videos.List list = youtube.videos().list(Collections.singletonList("statistics"));
        list.setId(Collections.singletonList(id));
        Video v = list.execute().getItems().get(0);

        YoutubeVideoStatistics statistics = new YoutubeVideoStatistics();

        statistics.setVideoId(id);
        statistics.setLikeCount(v.getStatistics().getLikeCount() !=null ? v.getStatistics().getLikeCount().longValue():0);
        statistics.setDislikeCount(v.getStatistics().getDislikeCount() != null ? v.getStatistics().getDislikeCount().longValue():0);
        statistics.setFavouriteCount(v.getStatistics().getFavoriteCount() != null ? v.getStatistics().getFavoriteCount().longValue():0);
        statistics.setCommentCount(v.getStatistics().getCommentCount() != null ? v.getStatistics().getCommentCount().longValue() : 0);
        statistics.setViewCount(v.getStatistics().getViewCount() != null ? v.getStatistics().getViewCount().longValue() : 0);


        youtubeVideoStatService.save(statistics);

        return statistics;
    }


    public YouTubeVideoInfo getCoontentDetails(String id, YouTubeVideoInfo youTubeVideoInfo) throws IOException {
        YouTube.Videos.List list = youtube.videos().list(Collections.singletonList("contentDetails"));
        list.setId(Collections.singletonList(id));
        Video v = list.execute().getItems().get(0);
        youTubeVideoInfo.setVideoDefinition(v.getContentDetails().getDefinition());
        youTubeVideoInfo.setVideoCaption(v.getContentDetails().getCaption());
        youTubeVideoInfo.setVideoprojection(v.getContentDetails().getProjection());
        youTubeVideoInfo.setCountryRestricted(v.getContentDetails().getCountryRestriction().toPrettyString());

        return youTubeVideoInfo;
    }
}

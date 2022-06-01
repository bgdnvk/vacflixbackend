package com.vacflix.backend.vacflixbackend.services.crawler;

public interface IApiCrawler {
    String crawlYoutubeVideoInfo(String keyword, long pageToCrawl);
}

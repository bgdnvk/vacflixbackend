package com.vacflix.backend.vacflixbackend.service;

public interface ApiCrawler {
    String crawlYoutubeVideoInfo(String keyword, long pageToCrawl);
}

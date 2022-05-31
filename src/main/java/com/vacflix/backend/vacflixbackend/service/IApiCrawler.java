package com.vacflix.backend.vacflixbackend.service;

public interface IApiCrawler {
    String crawlYoutubeVideoInfo(String keyword, long pageToCrawl);
}

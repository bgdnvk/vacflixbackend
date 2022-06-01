package com.vacflix.backend.vacflixbackend.services.crawlingInfo;

public interface IApiCrawlingInfo {
    String crawlYoutubeVideoInfo(String keyword, long pageToCrawl);
}

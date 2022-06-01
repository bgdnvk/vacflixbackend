package com.vacflix.backend.vacflixbackend.api;

import com.vacflix.backend.vacflixbackend.services.crawlingInfo.impl.ApiCrawlingInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrawlingInfoController {

    //get bean from services/crawlingInfo
    @Autowired
    ApiCrawlingInfoServiceImpl crawlingInfoService;

    //loads data info into crawling_info db
    @GetMapping(value = "ci/{keyword}/{page}")
    public String crawlingInfo(@PathVariable String keyword,
                               @PathVariable long page){
        return crawlingInfoService.crawlYoutubeVideoInfo(keyword, page);
    }
}

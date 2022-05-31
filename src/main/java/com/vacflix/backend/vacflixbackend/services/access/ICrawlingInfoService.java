package com.vacflix.backend.vacflixbackend.services.access;

import com.vacflix.backend.vacflixbackend.entity.CrawlingInfo;

import java.util.List;

public interface ICrawlingInfoService {
    void save(CrawlingInfo crawlingInfo);
    void update(CrawlingInfo crawlingInfo);
    CrawlingInfo get(long id);
    CrawlingInfo getBySearchKey(String searchKey);
    List<CrawlingInfo> getAll();
}

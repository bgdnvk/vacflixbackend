package com.vacflix.backend.vacflixbackend.service;

import com.vacflix.backend.vacflixbackend.entity.CrawlingInfo;
import com.vacflix.backend.vacflixbackend.repository.CrawlingInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CrawlingInfoBean implements CrawlingInfoService{
    @Autowired
    private CrawlingInfoRepository crawlingInfoRepository;

    @Override
    public void save(CrawlingInfo crawlingInfo) {
        crawlingInfoRepository.save(crawlingInfo);
    }

    @Override
    public void update(CrawlingInfo crawlingInfo) {
        crawlingInfoRepository.save(crawlingInfo);
    }

    @Override
    public CrawlingInfo get(long id) {
        return crawlingInfoRepository.getOne(id);
    }

    @Override
    public CrawlingInfo getBySearchKey(String searchKey) {
        return crawlingInfoRepository.findBySearchKey(searchKey);
    }

    @Override
    public List<CrawlingInfo> getAll() {
        return crawlingInfoRepository.findAll();
    }
}

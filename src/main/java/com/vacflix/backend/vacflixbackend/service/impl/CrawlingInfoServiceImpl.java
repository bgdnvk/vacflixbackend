package com.vacflix.backend.vacflixbackend.service.impl;

import com.vacflix.backend.vacflixbackend.entity.CrawlingInfo;
import com.vacflix.backend.vacflixbackend.repository.ICrawlingInfoRepository;
import com.vacflix.backend.vacflixbackend.service.ICrawlingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CrawlingInfoServiceImpl implements ICrawlingInfoService {
    @Autowired
    private ICrawlingInfoRepository ICrawlingInfoRepository;

    @Override
    public void save(CrawlingInfo crawlingInfo) {
        ICrawlingInfoRepository.save(crawlingInfo);
    }

    @Override
    public void update(CrawlingInfo crawlingInfo) {
        ICrawlingInfoRepository.save(crawlingInfo);
    }

    @Override
    public CrawlingInfo get(long id) {
        return ICrawlingInfoRepository.getOne(id);
    }

    @Override
    public CrawlingInfo getBySearchKey(String searchKey) {
        return ICrawlingInfoRepository.findBySearchKey(searchKey);
    }

    @Override
    public List<CrawlingInfo> getAll() {
        return ICrawlingInfoRepository.findAll();
    }
}

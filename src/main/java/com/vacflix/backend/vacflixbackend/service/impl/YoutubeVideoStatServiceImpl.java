package com.vacflix.backend.vacflixbackend.service.impl;

import com.vacflix.backend.vacflixbackend.entity.YoutubeVideoStatistics;
import com.vacflix.backend.vacflixbackend.repository.IYoutubeVideoStatisticsRepository;
import com.vacflix.backend.vacflixbackend.service.IYoutubeVideoStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YoutubeVideoStatServiceImpl implements IYoutubeVideoStatService {
    @Autowired
    private IYoutubeVideoStatisticsRepository IYoutubeVideoStatisticsRepository;

    @Override
    public void save(YoutubeVideoStatistics videoStatistics) {
        IYoutubeVideoStatisticsRepository.save(videoStatistics);
    }

    @Override
    public void update(YoutubeVideoStatistics videoStatistics) {
        IYoutubeVideoStatisticsRepository.save(videoStatistics);
    }

    @Override
    public YoutubeVideoStatistics get(long id) {
        return IYoutubeVideoStatisticsRepository.findById(id).get();
    }

    @Override
    public List<YoutubeVideoStatistics> getAll() {
        return IYoutubeVideoStatisticsRepository.findAll();
    }
}

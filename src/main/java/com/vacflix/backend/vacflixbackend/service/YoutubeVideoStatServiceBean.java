package com.vacflix.backend.vacflixbackend.service;

import com.vacflix.backend.vacflixbackend.entity.YouTubeVideoInfo;
import com.vacflix.backend.vacflixbackend.entity.YoutubeVideoStatistics;
import com.vacflix.backend.vacflixbackend.repository.YoutubeVideoStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YoutubeVideoStatServiceBean implements YoutubeVideoStatService{
    @Autowired
    private YoutubeVideoStatisticsRepository youtubeVideoStatisticsRepository;

    @Override
    public void save(YoutubeVideoStatistics videoStatistics) {
        youtubeVideoStatisticsRepository.save(videoStatistics);
    }

    @Override
    public void update(YoutubeVideoStatistics videoStatistics) {
        youtubeVideoStatisticsRepository.save(videoStatistics);
    }

    @Override
    public YoutubeVideoStatistics get(long id) {
        return youtubeVideoStatisticsRepository.findById(id).get();
    }

    @Override
    public List<YoutubeVideoStatistics> getAll() {
        return youtubeVideoStatisticsRepository.findAll();
    }
}

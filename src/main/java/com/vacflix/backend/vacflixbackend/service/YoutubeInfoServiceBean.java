package com.vacflix.backend.vacflixbackend.service;

import com.vacflix.backend.vacflixbackend.entity.YouTubeVideoInfo;
import com.vacflix.backend.vacflixbackend.repository.YouTubeVideoInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YoutubeInfoServiceBean implements YoutubeVideoInfoService{

    @Autowired
    private YouTubeVideoInfoRepository youtubeVideoInfoRepository;

    @Override
    public void save(YouTubeVideoInfo videoInfo) {
        youtubeVideoInfoRepository.save(videoInfo);
    }

    @Override
    public void update(YouTubeVideoInfo videoInfo) {
        youtubeVideoInfoRepository.save(videoInfo);
    }

    @Override
    public YouTubeVideoInfo getByVideoId(String videoId) {
        return youtubeVideoInfoRepository.findByVideoId(videoId);
    }

    @Override
    public YouTubeVideoInfo get(long id) {
        return youtubeVideoInfoRepository.findById(id).get();
    }

    @Override
    public List<YouTubeVideoInfo> getAll() {
        return youtubeVideoInfoRepository.findAll();
    }
}

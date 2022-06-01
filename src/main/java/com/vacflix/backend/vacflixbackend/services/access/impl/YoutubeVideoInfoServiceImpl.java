package com.vacflix.backend.vacflixbackend.services.access.impl;

import com.vacflix.backend.vacflixbackend.entity.YouTubeVideoInfo;
import com.vacflix.backend.vacflixbackend.repository.IYouTubeVideoInfoRepository;
import com.vacflix.backend.vacflixbackend.services.access.IYoutubeVideoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YoutubeVideoInfoServiceImpl implements IYoutubeVideoInfoService {

    @Autowired
    private IYouTubeVideoInfoRepository youtubeVideoInfoRepositoryI;

    @Override
    public void save(YouTubeVideoInfo videoInfo) {
        youtubeVideoInfoRepositoryI.save(videoInfo);
    }

    @Override
    public void update(YouTubeVideoInfo videoInfo) {
        youtubeVideoInfoRepositoryI.save(videoInfo);
    }

    @Override
    public YouTubeVideoInfo getByVideoId(String videoId) {
        return youtubeVideoInfoRepositoryI.findByVideoId(videoId);
    }

    @Override
    public YouTubeVideoInfo get(long id) {
        return youtubeVideoInfoRepositoryI.findById(id).get();
    }

    @Override
    public List<YouTubeVideoInfo> getAll() {
        return youtubeVideoInfoRepositoryI.findAll();
    }
}

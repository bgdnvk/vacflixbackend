package com.vacflix.backend.vacflixbackend.services.access;

import com.vacflix.backend.vacflixbackend.entity.YouTubeVideoInfo;

import java.util.List;

public interface IYoutubeVideoInfoService {
    void save(YouTubeVideoInfo videoInfo);
    void update(YouTubeVideoInfo videoInfo);
    YouTubeVideoInfo getByVideoId(String videoId);
    YouTubeVideoInfo get(long id);
    List<YouTubeVideoInfo> getAll();
}

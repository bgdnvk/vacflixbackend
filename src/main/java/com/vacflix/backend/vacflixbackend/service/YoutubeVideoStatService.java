package com.vacflix.backend.vacflixbackend.service;

import com.vacflix.backend.vacflixbackend.entity.YouTubeVideoInfo;
import com.vacflix.backend.vacflixbackend.entity.YoutubeVideoStatistics;

import java.util.List;

public interface YoutubeVideoStatService {
    void save(YoutubeVideoStatistics videoStatistics);
    void update(YoutubeVideoStatistics videoInfo);
    YoutubeVideoStatistics get(long id);
    List<YoutubeVideoStatistics> getAll();
}

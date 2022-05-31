package com.vacflix.backend.vacflixbackend.services.access;

import com.vacflix.backend.vacflixbackend.entity.YoutubeVideoStatistics;

import java.util.List;

public interface IYoutubeVideoStatService {
    void save(YoutubeVideoStatistics videoStatistics);
    void update(YoutubeVideoStatistics videoInfo);
    YoutubeVideoStatistics get(long id);
    List<YoutubeVideoStatistics> getAll();
}

package com.vacflix.backend.vacflixbackend.repository;

import com.vacflix.backend.vacflixbackend.entity.YouTubeVideoInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YoutubeVideoInfoRepository extends JpaRepository<YouTubeVideoInfo, Long> {
    YouTubeVideoInfo findByVideoId(String videoId);
}

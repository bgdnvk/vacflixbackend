package com.vacflix.backend.vacflixbackend.repository;

import com.vacflix.backend.vacflixbackend.entity.YoutubeVideoInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YoutubeVideoInfoRepository extends JpaRepository<YoutubeVideoInfo, Long> {
    YoutubeVideoInfo findByVideoId(String videoId);
}

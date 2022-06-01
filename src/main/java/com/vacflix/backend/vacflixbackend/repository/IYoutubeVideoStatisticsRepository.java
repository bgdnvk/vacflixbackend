package com.vacflix.backend.vacflixbackend.repository;

import com.vacflix.backend.vacflixbackend.entity.YoutubeVideoStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IYoutubeVideoStatisticsRepository extends JpaRepository<YoutubeVideoStatistics, Long> {
}

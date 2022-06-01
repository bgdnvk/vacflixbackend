package com.vacflix.backend.vacflixbackend.repository;

import com.vacflix.backend.vacflixbackend.entity.YoutubeChannelInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IYoutubeChannelRepository extends JpaRepository<YoutubeChannelInfo, Long> {
    public YoutubeChannelInfo findByChannelId(String channelId);
}

package com.vacflix.backend.vacflixbackend.service;

import com.vacflix.backend.vacflixbackend.entity.YoutubeChannelInfo;
import com.vacflix.backend.vacflixbackend.repository.IYoutubeChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YoutubeChannelServiceBean implements YoutubeChannelService{
    @Autowired
    IYoutubeChannelRepository IYoutubeChannelRepository;

    @Override
    public void save(YoutubeChannelInfo channelInfo) {
        IYoutubeChannelRepository.save(channelInfo);
    }

    @Override
    public void update(YoutubeChannelInfo channelInfo) {
        IYoutubeChannelRepository.save(channelInfo);
    }

    @Override
    public YoutubeChannelInfo get(long id) {
        return IYoutubeChannelRepository.findById(id).get();
    }

    @Override
    public YoutubeChannelInfo getByChannelId(String channelId) {
        return IYoutubeChannelRepository.findByChannelId(channelId);
    }

    @Override
    public List<YoutubeChannelInfo> getAll() {
        return IYoutubeChannelRepository.findAll();
    }
}

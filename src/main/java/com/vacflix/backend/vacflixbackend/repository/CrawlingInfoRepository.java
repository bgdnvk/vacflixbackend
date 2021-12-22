package com.vacflix.backend.vacflixbackend.repository;

import com.vacflix.backend.vacflixbackend.entity.CrawlingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrawlingInfoRepository extends JpaRepository<CrawlingInfo, Long> {
    CrawlingInfo findBySearchKey(String searchKey);
}

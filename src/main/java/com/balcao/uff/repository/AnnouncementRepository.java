package com.balcao.uff.repository;

import com.balcao.uff.domain.Announcement;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface AnnouncementRepository extends Repository<Announcement, Long> {
    List<Announcement> findAll();
}


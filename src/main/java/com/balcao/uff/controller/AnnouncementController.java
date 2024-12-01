package com.balcao.uff.controller;

import com.balcao.uff.domain.Announcement;
import com.balcao.uff.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    @GetMapping(value = "/anuncios")
    public ResponseEntity<List<Announcement>> findAll() {
        final List<Announcement> announcements = announcementService.findAll();
        return ResponseEntity.ok()
                .body(announcements);
    }
}

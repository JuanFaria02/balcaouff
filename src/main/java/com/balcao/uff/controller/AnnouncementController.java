package com.balcao.uff.controller;

import com.balcao.uff.domain.Announcement;
import com.balcao.uff.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @GetMapping(value = "/anuncio/{id}")
    public ResponseEntity<Announcement> findById(@PathVariable Long id) {
        final Announcement announcement = announcementService.findById(id);
        return ResponseEntity.ok().body(announcement);
    }

    @PostMapping("/anuncio/criar")
    public ResponseEntity<Announcement> insert(@RequestBody Announcement obj) {
        obj = announcementService.insert(obj);
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping("/anuncio/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        announcementService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/anuncio/{id}")
    public ResponseEntity<Announcement> update(@RequestBody Announcement obj) {
        obj = announcementService.update(obj);
        return ResponseEntity.ok().body(obj);
    }
}

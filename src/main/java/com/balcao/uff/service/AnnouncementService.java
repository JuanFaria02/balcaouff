package com.balcao.uff.service;

import com.balcao.uff.domain.Announcement;
import com.balcao.uff.repository.AnnouncementRepository;
import com.balcao.uff.service.exceptions.DatabaseException;
import com.balcao.uff.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnnouncementService {
    @Autowired
    private AnnouncementRepository announcementRepository;

    public List<Announcement> findAll() {
        return announcementRepository.findAll();
    }

    public Announcement findById(Long id) {
        Optional<Announcement> obj = announcementRepository.findById(id);
        return obj.orElseThrow(()-> new ResourceNotFoundException(id));
    }

    public void delete(Long id) {
        try {
            announcementRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Announcement update(Announcement obj) {
        if (obj.getId() == null) {
            throw new DatabaseException("id is required");
        }
        return announcementRepository.save(obj);
    }

    public Announcement insert(Announcement announcement) {
        if (announcement.getId() != null) {
            throw new DatabaseException("Esse anúncio já existe");
        }
        return announcementRepository.save(announcement);
    }
}

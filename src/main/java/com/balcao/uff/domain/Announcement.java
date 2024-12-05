package com.balcao.uff.domain;

import com.balcao.uff.domain.enums.AnnouncementType;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "announcement")
public class Announcement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "text", nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AnnouncementType type;

    @Column
    @Timestamp
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private String location;

    @Column
    private boolean active;

    @Column
    private Long rating;

    @Column(precision = 15, scale = 2)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Announcement() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AnnouncementType getType() {
        return type;
    }

    public void setType(AnnouncementType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

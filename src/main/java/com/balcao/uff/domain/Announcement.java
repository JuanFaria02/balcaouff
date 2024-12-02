package com.balcao.uff.domain;

import com.balcao.uff.domain.enums.AnnouncementType;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "announcement")
@AllArgsConstructor
@NoArgsConstructor
@Getter
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

    @Column
    private boolean active;

    @Column
    private Long rating;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

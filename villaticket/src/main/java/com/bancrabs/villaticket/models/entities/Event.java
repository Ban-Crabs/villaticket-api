package com.bancrabs.villaticket.models.entities;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "event")
public class Event {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "type_id", nullable = true)
    @ManyToOne(fetch = FetchType.EAGER)
    private Type type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id", nullable = true)
    private Location location;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "start_time", nullable = true)
    private Timestamp startTime;

    @Column(name = "end_time", nullable = true)
    private Timestamp endTime;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "visibility", nullable = false)
    private Boolean isVisible;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Sponsor> sponsors;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Category> categories;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Image> images;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Attendance> attendances;
}

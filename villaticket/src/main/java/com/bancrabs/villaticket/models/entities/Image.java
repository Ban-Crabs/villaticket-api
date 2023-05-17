package com.bancrabs.villaticket.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "image")
public class Image {
    
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "url", nullable = true)
    private String url;

    @Column(name = "event_id", nullable = true)
    @ManyToOne(fetch = FetchType.EAGER)
    private Event event;

}

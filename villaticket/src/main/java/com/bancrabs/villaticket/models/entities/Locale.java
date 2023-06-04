package com.bancrabs.villaticket.models.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "locale", schema = "public")
public class Locale {
    
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    public Locale(String id, String name){
        this.id = id;
        this.name = name;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Tier> tiers;
}

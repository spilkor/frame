package com.spilkor.frame.model;

import javax.persistence.*;

@MappedSuperclass
public abstract class BusinessEntity extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
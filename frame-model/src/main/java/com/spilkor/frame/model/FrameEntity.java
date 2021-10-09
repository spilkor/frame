package com.spilkor.frame.model;

import javax.persistence.*;

@MappedSuperclass
public abstract class FrameEntity extends BaseEntity<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private String id;

    @Column(name = "REFERENCE_NAME")
    private String referenceName;


    @Override
    String getId() {
        return id;
    }

    @Override
    void setId(String id) {
        this.id = id;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

}

package com.spilkor.frame.model;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity <ID> {

    abstract ID getId();

    abstract void setId(ID id);


    @Column(name = "ENTITY_DESCRIPTOR_ID")
    private String entityDescriptorId;

    @Column(name = "STATE")
    private String state;


    public String getEntityDescriptorId() {
        return entityDescriptorId;
    }

    public void setEntityDescriptorId(String entityDescriptorId) {
        this.entityDescriptorId = entityDescriptorId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
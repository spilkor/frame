package com.spilkor.frame.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FRM_FIELD_DESCRIPTOR")
public class FieldDescriptor extends FrameEntity {

    @Column(name = "TYPE")
    private String type;

    @Column(name = "MAIN_ENTITY_DESCRIPTOR_ID")
    private String mainEntityDescriptorId;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMainEntityDescriptorId() {
        return mainEntityDescriptorId;
    }

    public void setMainEntityDescriptorId(String mainEntityDescriptorId) {
        this.mainEntityDescriptorId = mainEntityDescriptorId;
    }
}

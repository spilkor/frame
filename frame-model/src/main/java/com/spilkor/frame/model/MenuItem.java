package com.spilkor.frame.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FRM_MENU_ITEM")
public class MenuItem extends FrameEntity {

    @Column(name = "PARENT_ID")
    private String parentId;

    @Column(name = "PAGE_ID")
    private String pageId;

    @Column(name = "TEXT")
    private String text;


    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

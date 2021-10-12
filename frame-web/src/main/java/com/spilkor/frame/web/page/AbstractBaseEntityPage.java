package com.spilkor.frame.web.page;

import com.spilkor.frame.model.BaseEntity;

public abstract class AbstractBaseEntityPage<T extends BaseEntity> extends FramePage{

    protected T entity;

    public AbstractBaseEntityPage(T entity){
        this.entity = entity;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity){
        this.entity = entity;
    }

}

package com.spilkor.frame.repository.frameentity;

import com.spilkor.frame.model.FrameEntity;

public interface CustomFrameEntityRepository {

    <CLAZZ extends FrameEntity> CLAZZ find(Class<CLAZZ> clazz, String id);

}


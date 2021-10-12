package com.spilkor.service;

import com.spilkor.frame.model.FrameEntity;
import com.spilkor.frame.repository.frameentity.FrameEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FrameEntityService extends FrameService {

    private final FrameEntityRepository frameEntityRepository;

    @Autowired
    public FrameEntityService(FrameEntityRepository frameEntityRepository){
        this.frameEntityRepository = frameEntityRepository;
    }

    public <CLAZZ extends FrameEntity> CLAZZ find(Class<CLAZZ> clazz, String id) {
        return frameEntityRepository.find(clazz, id);
    }

}

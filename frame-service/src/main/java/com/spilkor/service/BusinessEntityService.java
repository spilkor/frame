package com.spilkor.service;

import com.spilkor.frame.model.BusinessEntity;
import com.spilkor.frame.repository.businessentity.BusinessEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BusinessEntityService extends FrameService {

    private final BusinessEntityRepository businessEntityRepository;

    @Autowired
    public BusinessEntityService(BusinessEntityRepository businessEntityRepository){
        this.businessEntityRepository = businessEntityRepository;
    }

    public <CLAZZ extends BusinessEntity> CLAZZ find(Class<CLAZZ> clazz, Long id) {
        return businessEntityRepository.find(clazz, id);
    }


}

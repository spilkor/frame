package com.spilkor.frame.repository.businessentity;

import com.spilkor.frame.model.BusinessEntity;

public interface CustomBusinessEntityRepository {

    <CLAZZ extends BusinessEntity> CLAZZ find(Class<CLAZZ> clazz, Long id);

}


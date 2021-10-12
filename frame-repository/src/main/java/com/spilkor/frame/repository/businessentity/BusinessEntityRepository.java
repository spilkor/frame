package com.spilkor.frame.repository.businessentity;

import com.spilkor.frame.model.BusinessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessEntityRepository extends JpaRepository<BusinessEntity, Long>, CustomBusinessEntityRepository {

}
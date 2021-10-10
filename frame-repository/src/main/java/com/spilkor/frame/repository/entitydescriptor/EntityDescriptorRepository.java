package com.spilkor.frame.repository.entitydescriptor;

import com.spilkor.frame.model.EntityDescriptor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityDescriptorRepository extends JpaRepository<EntityDescriptor, Long>, CustomEntityDescriptorRepository {

    EntityDescriptor findByEntityDescriptorId(String entityDescriptorId);

}
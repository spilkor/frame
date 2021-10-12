package com.spilkor.frame.repository.frameentity;

import com.spilkor.frame.model.FrameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FrameEntityRepository extends JpaRepository<FrameEntity, String>, CustomFrameEntityRepository {

}
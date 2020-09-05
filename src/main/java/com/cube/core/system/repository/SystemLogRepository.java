package com.cube.core.system.repository;

import com.cube.core.system.entity.SystemLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemLogRepository extends JpaRepository<SystemLog,String> {
}

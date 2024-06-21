package com.schoolproject.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;

public interface CommonRepository {
	
	@Modifying
    @Transactional
    @Query("Alter table :tableName auto_increment = 1")
    void updateDeadline(@Param("tableName") String tableName);

}

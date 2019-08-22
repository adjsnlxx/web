package com.web.dao.repository;

import com.web.dao.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TestRepository extends JpaRepository<TestEntity, Long> {

	//使用原生sql时Query注解需要添加属性，nativeQuery=true
	@Query(nativeQuery = true, value = "select max(Id) from test")
	long getMaxId();
}

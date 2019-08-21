package com.web.service;

import com.web.dao.entity.TestEntity;
import com.web.repository.TestRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class TestService {

	@Resource
	private TestRepository testRepository;

	public Optional<TestEntity> getTestById(Long id) {
		return testRepository.findById(id);
	}
}
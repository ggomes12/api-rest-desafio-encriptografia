package com.ggomes.api_encryption.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ggomes.api_encryption.entities.EncryptEntity;
import com.ggomes.api_encryption.repositories.EncryptRepository;

@Service
public class EncryptService {

	@Autowired
	private EncryptRepository repository;


    public List<EncryptEntity> findAll() {
        return repository.findAll();
    }

    public EncryptEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    public EncryptEntity save(EncryptEntity transaction) {
        return repository.save(transaction);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

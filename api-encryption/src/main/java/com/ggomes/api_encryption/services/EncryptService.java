package com.ggomes.api_encryption.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ggomes.api_encryption.entities.EncryptEntity;
import com.ggomes.api_encryption.exceptions.InvalidRequestException;
import com.ggomes.api_encryption.exceptions.ResourceNotFoundException;
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
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with ID: " + id));
    }

    public EncryptEntity save(EncryptEntity transaction) {
    	if (transaction.getValue() <= 0) {
            throw new InvalidRequestException("Transaction value must be greater than zero");
        }
        return repository.save(transaction);
    }

    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete, transaction not found with ID: " + id);
        }
        repository.deleteById(id);
    }
}

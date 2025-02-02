package com.ggomes.api_encryption.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ggomes.api_encryption.entities.EncryptEntity;

public interface EncryptRepository extends JpaRepository<EncryptEntity, Long> {

}

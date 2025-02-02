package com.ggomes.api_encryption.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ggomes.api_encryption.entities.EncryptEntity;
import com.ggomes.api_encryption.services.EncryptService;

@RestController
@RequestMapping("/api/sensitive-data")
public class EncryptController {

	@Autowired
    private EncryptService service;

   

    @GetMapping
    public List<EncryptEntity> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public EncryptEntity getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public EncryptEntity create(@RequestBody EncryptEntity data) {
        return service.save(data);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}

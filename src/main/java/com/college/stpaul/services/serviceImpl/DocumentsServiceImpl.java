package com.college.stpaul.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.college.stpaul.entities.Documents;
import com.college.stpaul.repository.DocumentsRepo;
import com.college.stpaul.services.serviceInterface.DocumentService;

public class DocumentsServiceImpl implements DocumentService{

    @Autowired
    private DocumentsRepo documentsRepo;

    @Override
    public Documents addDocuments(Documents documents) {
        return this.documentsRepo.save(documents);
    }
    
}

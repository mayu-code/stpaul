package com.college.stpaul.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.stpaul.entities.Documents;
import com.college.stpaul.repository.DocumentsRepo;
import com.college.stpaul.services.serviceInterface.DocumentService;

@Service
public class DocumentsServiceImpl implements DocumentService{

    @Autowired
    private DocumentsRepo documentsRepo;

    @Override
    public Documents addDocuments(Documents documents) {
        return this.documentsRepo.save(documents);
    }
    
}

package com.example.demo.document.service;

import com.example.demo.document.model.MyDocument;

public class DocumentServiceImpl implements IDocumentService {

    @Override
    public MyDocument getDocumentById(long id) {
        return new MyDocument(1L, "Mi primer document");
    }
}

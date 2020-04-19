package com.example.demo.document.service;

import com.itextpdf.text.DocumentException;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public interface IDocumentGeneratorService {

    ByteArrayOutputStream generate(Object data, String template)
            throws IOException, DocumentException;
}

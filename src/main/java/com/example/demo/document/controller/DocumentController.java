package com.example.demo.document.controller;

import com.example.demo.document.model.MyDocument;
import com.example.demo.document.service.IDocumentGeneratorService;
import com.example.demo.document.service.IDocumentService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping(path="/documents")
public class DocumentController {

    public static final String DOCUMENT_TEMPLATE = "template/factura";
    private final IDocumentService documentService;
    private final IDocumentGeneratorService generatorService;

    public DocumentController(IDocumentService documentService,
            IDocumentGeneratorService generatorService) {
        this.documentService = documentService;
        this.generatorService = generatorService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity generateDocument(@PathVariable("id") final Long id) throws Exception {
        final MyDocument myDocument = this.documentService.getDocumentById(id);
        final ByteArrayOutputStream document = this.generatorService.generate(myDocument, DOCUMENT_TEMPLATE);
        final ByteArrayResource resource = new ByteArrayResource(document.toByteArray());
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(resource.contentLength())
                .body(resource);
    }
}

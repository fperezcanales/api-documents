package com.example.demo.document.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public interface IDocumentGeneratorService {

    ByteArrayOutputStream generate(Object data, String template)
        throws IOException;
}

package com.example.demo.document.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.nio.file.FileSystems;

import com.example.demo.AppProperties;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

@Service
public class DocumentGeneratorServiceImpl implements IDocumentGeneratorService{

    private final ITemplateEngine templateEngine ;
    private final ITextRenderer textRenderer ;
    private final String baseUrl;

    public DocumentGeneratorServiceImpl(ITemplateEngine templateEngine,
                                        ITextRenderer textRenderer,
                                        AppProperties properties) throws MalformedURLException {
        this.templateEngine = templateEngine;
        this.textRenderer = textRenderer;
        this.baseUrl = FileSystems
                .getDefault().getPath(properties.getTemplates(), "static")
                .toUri().toURL().toString();
    }

    @Override
    public ByteArrayOutputStream generate(Object data, String template) throws IOException, DocumentException {
        final Context context = new Context();
        context.setVariable("data", data);
        final String renderedHtmlContext = this.templateEngine.process(template, context);
        final String xHtml = convertToXhtml(renderedHtmlContext);
        this.textRenderer.setDocumentFromString(xHtml, this.baseUrl);
        this.textRenderer.layout();

        try( final ByteArrayOutputStream output = new ByteArrayOutputStream()){
            this.textRenderer.createPDF(output, true );
            return output;
        }
    }

    private String convertToXhtml(final String html) throws UnsupportedEncodingException {
        final Tidy tidy = new Tidy() {
            {
                setInputEncoding("UTF-8");
                setOutputEncoding("UTF-8");
                setXHTML(true);
            }
        };
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(html.getBytes());
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        tidy.parse(inputStream,outputStream);
        return outputStream.toString("UTF-8");
    }

}

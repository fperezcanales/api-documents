package com.example.demo;

import com.itextpdf.text.DocumentException;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties(AppProperties.class)
public class AppConfig {

    public static final String ENCODING = "UTF-8";

    private final AppProperties properties;

    public AppConfig(AppProperties properties) {
        this.properties = properties;
    }

    @Bean
    public ITemplateResolver getTemplateResolver() {
        return new FileTemplateResolver() {{
            setPrefix(AppConfig.this.properties.getTemplates());
            setSuffix(".html");
            setTemplateMode(TemplateMode.HTML);
            setCharacterEncoding(ENCODING);
        }};
    }

    @Bean
    public ITextRenderer getITextRenderer() throws IOException, DocumentException {
        return new ITextRenderer() {{
            /*getFontResolver()
                    .addFont(ApcConfig.this.properties.getTemplates() + "static/Code39.ttf",
                            IDENTITY_H, EMBEDDED);*/
        }};
    }

    @Bean
    public TemplateEngine getTemplateEngine(
            final ITemplateResolver templateResolver/*, final ChasDialect dialect*/) {
        return new TemplateEngine() {{
            this.setCacheManager(null);
            this.setTemplateResolver(templateResolver);
            //this.setAdditionalDialects(Collections.singleton(dialect));
        }};
    }
}

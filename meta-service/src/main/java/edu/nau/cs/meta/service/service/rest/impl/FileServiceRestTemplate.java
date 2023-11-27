package edu.nau.cs.meta.service.service.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FileServiceRestTemplate extends AbstractRestTemplateDecorator {

    @Autowired
    public FileServiceRestTemplate(RestTemplate restTemplate,
                                   @Value("${file.service.url.schema}") String schema,
                                   @Value("${file.service.host}") String host) {
        super(restTemplate, schema, host);
    }

}

package edu.nau.cs.file.service.service.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MetaServiceRestTemplate extends AbstractRestTemplateDecorator {

    @Autowired
    public MetaServiceRestTemplate(RestTemplate restTemplate,
                                   @Value("${meta.service.url.schema}") String schema,
                                   @Value("${meta.service.host}") String host) {
        super(restTemplate, schema, host);
    }

}

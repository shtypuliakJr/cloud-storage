package edu.nau.cs.meta.service.service.rest.impl;

import edu.nau.cs.meta.service.exception.RestException;
import edu.nau.cs.meta.service.service.rest.RestTemplateDecorator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@Log4j2
public class AbstractRestTemplateDecorator implements RestTemplateDecorator {

    public static final String ERROR_MESSAGE_REST_QUERY = "%s request to %s with http status=%s isn't successful";
    private final RestTemplate restTemplate;
    private final String schema;
    private final String host;

    @Override
    public <T> T postForEntity(String path, Object request, Class<T> responseType) {
        ResponseEntity<T> response;
        try {
            response = restTemplate.postForEntity(getURI(path), request, responseType);
        } catch (HttpClientErrorException ex) {
            log.error("Bad client response - {}", ex.getMessage());
            throw new RestException(ex.getMessage());
        } catch (Exception e) {
            log.error("Bad response", e);
            throw new RestException(e.getMessage());
        }
        checkHttpStatus(response, path, RequestMethod.POST);
        return response.getBody();
    }

    @Override
    public <T> T getForEntity(String path, Class<T> responseType) {
        ResponseEntity<T> response;
        try {
            response = restTemplate.getForEntity(getURI(path), responseType);
        } catch (HttpClientErrorException ex) {
            log.error("Bad client response - {}", ex.getMessage());
            throw new RestException(ex.getMessage());
        } catch (Exception e) {
            log.error("Bad response", e);
            throw new RestException(e.getMessage());
        }
        checkHttpStatus(response, path, RequestMethod.GET);
        return response.getBody();
    }

    private URI getURI(String path) {
        return UriComponentsBuilder.newInstance()
                .scheme(schema)
                .host(host)
                .path(path)
                .build()
                .toUri();
    }

    private <T> void checkHttpStatus(@NonNull ResponseEntity<T> response, String path, RequestMethod requestMethod) throws RestException {
        HttpStatus httpStatus = response.getStatusCode();
        if (!httpStatus.is2xxSuccessful()) {
            throw new RestException(String.format(ERROR_MESSAGE_REST_QUERY, requestMethod, path, httpStatus));
        }
    }

}

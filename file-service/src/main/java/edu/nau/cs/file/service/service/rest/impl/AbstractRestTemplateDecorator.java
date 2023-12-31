package edu.nau.cs.file.service.service.rest.impl;

import edu.nau.cs.file.service.exception.RestException;
import edu.nau.cs.file.service.service.rest.RestTemplateDecorator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Log4j2
public class AbstractRestTemplateDecorator implements RestTemplateDecorator {

    public static final String ERROR_MESSAGE_REST_QUERY = "%s request to %s with http status=%s isn't successful";
    private final RestTemplate restTemplate;
    private final String schema;
    private final String host;

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
    public void deleteForEntity(String path) {
        try {
            restTemplate.delete(getURI(path));
        } catch (HttpClientErrorException ex) {
            log.error("Bad client response - {}", ex.getMessage());
            throw new RestException(ex.getMessage());
        } catch (Exception e) {
            log.error("Bad response", e);
            throw new RestException(e.getMessage());
        }
    }

    @Override
    public void deleteForEntities(String path, Map<String, ?> queryParams) {
        try {
            restTemplate.delete(getURI(path).toURL().toString(), queryParams);
        } catch (HttpClientErrorException ex) {
            log.error("Bad client response - {}", ex.getMessage());
            throw new RestException(ex.getMessage());
        } catch (Exception e) {
            log.error("Bad response", e);
            throw new RestException(e.getMessage());
        }
    }

    @Override
    public <T> T deleteForEntityWithResponse(String path, Class<T> responseType) {
        ResponseEntity<T> response;
        try {
            response = restTemplate.exchange(getURI(path), HttpMethod.DELETE, HttpEntity.EMPTY, responseType);
        } catch (HttpClientErrorException ex) {
            log.error("Bad client response - {}", ex.getMessage());
            throw new RestException(ex.getMessage());
        } catch (Exception e) {
            log.error("Bad response", e);
            throw new RestException(e.getMessage());
        }
        checkHttpStatus(response, path, RequestMethod.DELETE);
        return response.getBody();
    }

    @Override
    public <T> List<T> deleteForEntitiesWithResponse(final String path, Map<String, ?> queryParams) {
        ResponseEntity<List<T>> response;
        try {
            response = restTemplate.exchange(
                    getURI(path).toURL().toString(),
                    HttpMethod.DELETE,
                    HttpEntity.EMPTY,
                    new ParameterizedTypeReference<>() {
                    },
                    queryParams);
        } catch (HttpClientErrorException ex) {
            log.error("Bad client response - {}", ex.getMessage());
            throw new RestException(ex.getMessage());
        } catch (Exception e) {
            log.error("Bad response", e);
            throw new RestException(e.getMessage());
        }
        checkHttpStatus(response, path, RequestMethod.DELETE);
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

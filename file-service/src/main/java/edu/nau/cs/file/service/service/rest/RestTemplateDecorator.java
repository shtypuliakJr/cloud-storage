package edu.nau.cs.file.service.service.rest;

import java.util.List;
import java.util.Map;

public interface RestTemplateDecorator {

    <T> T getForEntity(String path, Class<T> responseType);

    <T> T postForEntity(String path, Object request, Class<T> responseType);

    void deleteForEntity(String path);

    void deleteForEntities(String path, Map<String, ?> queryParams);

    <T> T deleteForEntityWithResponse(String path, Class<T> responseType);

    <T> List<T> deleteForEntitiesWithResponse(String path, Map<String, ?> queryParams);

}

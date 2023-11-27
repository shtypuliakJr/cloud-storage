package edu.nau.cs.file.service.service.rest;

import edu.nau.cs.file.service.exception.RestException;
import org.springframework.lang.Nullable;

public interface RestTemplateDecorator {

    <T> T postForEntity(String path, @Nullable Object request, Class<T> responseType) throws RestException;

    <T> T getForEntity(String path, Class<T> responseType) throws RestException;

}

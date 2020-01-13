package com.daemongear.beta.service;

/**
 * UrlService
 */
public interface UrlService {

    public boolean ytUrlIsValid(String url);
    public boolean downloadFile(String url);
}
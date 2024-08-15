package com.example.softaria_project.service;

import java.net.URI;
import java.util.Set;

public interface PageService {
    void addPastData(URI pastUri);

    void addPresentData(URI presentUri);

    Set<URI> getPastData();

    Set<URI> getPresentData();

    void sendEmail(String email);

    void removePastData(URI uri);

    void removePresentData(URI uri);
}

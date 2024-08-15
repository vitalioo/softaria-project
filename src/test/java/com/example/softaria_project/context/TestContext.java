package com.example.softaria_project.context;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class TestContext {
    private final Map<URI, String> presentData = new HashMap<>();

    public Map<URI, String> getPresentData() {
        return presentData;
    }

}

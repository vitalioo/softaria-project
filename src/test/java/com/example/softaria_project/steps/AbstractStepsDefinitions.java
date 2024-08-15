package com.example.softaria_project.steps;

import com.example.softaria_project.context.TestContext;
import com.example.softaria_project.service.PageService;
import com.github.tomakehurst.wiremock.WireMockServer;

import java.net.URI;
import java.util.Map;

public class AbstractStepsDefinitions {

    private final TestContext context = new TestContext();

    protected WireMockServer wireMockServer;

    protected PageService pageService;

    public AbstractStepsDefinitions(WireMockServer wireMockServer, PageService pageService) {
        this.wireMockServer = wireMockServer;
        this.pageService = pageService;
    }

    public Map<URI, String> getContext() {
        return context.getPresentData();
    }

}

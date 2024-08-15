package com.example.softaria_project.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WireMockConfig {

    @Bean
    public WireMockServer wireMockServer() {
        WireMockServer server = new WireMockServer(new WireMockConfiguration().dynamicPort());
        server.start();
        WireMock.configureFor(server.getOptions().bindAddress(), server.port());
        return server;
    }
}
package com.example.softaria_project.config;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.FEATURES_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.example.softaria_project")
@ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "src/test/resources/features/")
public class EndToEndCucumberTestConfiguration {
}
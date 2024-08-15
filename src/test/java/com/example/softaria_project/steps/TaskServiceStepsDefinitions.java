package com.example.softaria_project.steps;

import com.example.softaria_project.service.PageService;
import com.github.tomakehurst.wiremock.WireMockServer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.awaitility.Awaitility;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@Slf4j
public class TaskServiceStepsDefinitions extends AbstractStepsDefinitions {
    private final URI url = URI.create("https://vk.com");

    public TaskServiceStepsDefinitions(WireMockServer wireMockServer, PageService pageService) {
        super(wireMockServer, pageService);
    }

    @Given("the page service will successfully save page data")
    public void thePageServiceWillSuccessfullySavePageData() {
        mockPageService();
    }

    private void mockPageService() {
        wireMockServer.stubFor(post(urlEqualTo("/api/v1/pages/present-url"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")));
    }

    @When("I create a page with url {string}")
    public void iCreateAPageWithUrl(String url) {
        getContext().put(URI.create(url), "<html></html>");
    }

    @Then("the page should be saved successfully")
    public void thePageShouldBeSavedSuccessfully() {
        Awaitility.await()
                .atMost(10, TimeUnit.SECONDS)
                .pollInterval(2, TimeUnit.SECONDS)
                .until(() -> {
                    String html = getContext().get(url);
                    return !html.isBlank();
                });
    }

    @Given("the page service will delete a page with url {string}")
    public void thePageServiceWillDeleteAPageWithUrl() {
        wireMockServer.stubFor(delete(urlEqualTo("/api/v1/pages/delete-present-url"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")));
    }

    @When("I delete the page with url {string}")
    public void iDeleteThePageWithUrl(String url) {
        getContext().remove(URI.create(url));
    }

    @Then("the page should be deleted successfully")
    public void thePageShouldBeDeletedSuccessfully() {
        Awaitility.await()
                .atMost(10, TimeUnit.SECONDS)
                .pollInterval(2, TimeUnit.SECONDS)
                .until(() -> getContext().get(url) == null);
    }
}

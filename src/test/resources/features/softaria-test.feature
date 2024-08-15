Feature: Life cycle of page

  Scenario: Create, save and retrieves page

    Given the page service will successfully save page data
    When I create a page with url "https://vk.com"
    Then the page should be saved successfully

    Given the page service will delete a page with url "https://vk.com"
    When I delete the page with url "https://vk.com"
    Then the page should be deleted successfully
package com.alemdian.test;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class MyStepdefs {



    @Before
    public void setup() {
        Configurator.setupProject();
        open("https://test.animana.com/web2/login");
        $("#username").val("testnl40");
        $("#password").val("Test#12345");
        $("#logon-login-button").click();
        $$("form input[type='button']").filter(value("Hilversum")).first().click();
    }

    @Given("^I am logged in user$")
    public void iAmLoggedInUser() {
        switchTo().frame("mana");
        $("#topMenuBar-lastName-info").shouldHave(text("kukrani"));
        $(".location-selected.tooltip-left.icon-location").shouldHave(text("Hilversum"));
    }

    @When("^I am on the main page$")
    public void iAmOnTheMainPage() {
        assert getWebDriver().getCurrentUrl().equals("https://testnl.test.animana.com/");
    }

    @Then("^I see new contact icon$")
    public void iSeeNewContactIcon() {
        $("#topMenuBar-addNewClient-button").shouldBe(visible);
    }

    @And("^I am selecting (.+) from the search drop down$")
    public void iAmSelectingPatientFromTheSearchDropDown(String menuItem) {
        switchTo().frame("mana");
        $("#topMenuBar-searchDropdown-button").click();
        $$("ul > li a").filter(text(menuItem)).first().click();
    }

    @When("^I am typing '(.+)' in the search bar$")
    public void iAmTypingPatientNameInTheSearchBar(String patientName) {
        $("#headerSearchInput").val("Diensthond");
    }

    @And("^I click on the Search button$")
    public void iClickOnTheSearchButton() {
        $("#mainSearchButton").click();
    }

    @Then("^I see patient with below details in search result:$")
    public void iSeePatientWithBelowDetailsInSearchResult(DataTable results) {

        HashMap<String, String> expectedResults = new HashMap<>();
        Map<String, String> stringStringMap = results.asMaps(String.class, String.class).get(0);

        HashMap<String, String> actualResults = new HashMap<>();

        int numberOfColumns = $("#findResult-searchResult-table").$$("thead tr th").size();
        for (int i = 0; i < numberOfColumns; i++) {
            String headerName = $$("#findResult-searchResult-table thead tr th").get(i).text();
            String value = $$("#findResult-searchResult-table tbody tr td").get(i).text();

            if (i == 0 && headerName.equals(" ")) {
                headerName = "Gender";
                if ($$("#findResult-searchResult-table tbody tr td")
                        .get(i)
                        .$("i")
                        .attr("class")
                        .equals("icon-male")) {
                    value = "male";
                } else {
                    value = "female";
                }
            }
            actualResults.put(headerName, value);
        }
        assertThat(actualResults).isEqualToComparingOnlyGivenFields(stringStringMap);
    }

    @After
    public void tearDown() {
        close();
    }
}

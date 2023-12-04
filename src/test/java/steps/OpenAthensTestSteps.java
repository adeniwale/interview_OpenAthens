package steps;

import org.junit.Assert;
import pageObjects.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javax.inject.Inject;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class OpenAthensTestSteps {

    @Inject private DashboardPage oaDashboardPageObj;
    @Inject private HomePage oaHomePageObj;
    @Inject private RegistrationPage oaRegistrationPageObj;

    RequestSpecification requestSpec;
    Response response;

    @Given("I navigate to the OpenAthens Registration webpage")
    public void iNavigateToTheOpenAthensRegistrationWebpage() throws Exception {
        oaHomePageObj.NavigateToOpenAthensRegistrationPage();
    }

    @Given("I am on the OpenAthens Home webpage")
    public void iAmOnOpenAthensHomeWebpage() {
        oaHomePageObj.EnsureOpenAthensHomePageIsOpened();
    }

    @When("I register the student details - {string}, {string}, {string}, {string}, {string} and {string}")
    public void iEnterTheStudentDetails(String firstname, String email, String web, String interests, String password, String confirm) {
        oaRegistrationPageObj.RegisterAndNavigateToOpenAthensDashboardPage(firstname, email, web, interests, password, confirm);
    }

    @Then("a successful registration for {string} is displayed on the OpenAthens Dashboard webpage")
    public void aSuccessfulRegistrationForIsDisplayedOnTheDashboardWebpage(String firstname) {
        oaDashboardPageObj.assertSuccessfulRegistration(firstname);
    }

    @Then("a/an {} validation error message {string} is displayed on the OpenAthens Registration webpage")
    public void aValidationErrorIsDisplayedOnTheOpenAthensRegistrationWebpage(String errorType, String errorMsg) {
        switch (errorType) {
            case "confirm password":
                oaRegistrationPageObj.assertConfirmValidationError(errorMsg);
                break;
            case "email":
                oaRegistrationPageObj.assertEmailValidationError(errorMsg);
                break;
            case "password":
                oaRegistrationPageObj.assertPasswordValidationError(errorMsg);
                break;
        }
    }

    // Possible step definitions for Task 2b
    @Given("I am using OpenAthens API")
    public void iAmUsingOpenAthensApi() {
        // Create request
        requestSpec = new RequestSpecBuilder().build();
        requestSpec.baseUri("");
    }

    @When("I call POST API for Registration request")
    public void iCallPostApiForRegistrationRequest() {
        // Get response for the API call
        response = given()
                .spec(requestSpec)
                .post();
    }

    @Then("the response status is {int}")
    public void theResponseStatusIs(long statusCode) {
        // Assert response status code
        Assert.assertEquals(response.getStatusCode(), statusCode);
    }
}
package steps;

import dataHolder.DataHolder;
import drivers.ChromeDriver;
import org.openqa.selenium.NoSuchElementException;
import pageObjects.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.io.FileNotFoundException;
import java.util.*;

public class FreeCarCheckTestSteps {

    private final DataHolder dataHolder;
    private final DetailsPageObjects fccDetailsPO;
    private final HomePageObjects fccHomePO;

    public FreeCarCheckTestSteps(DataHolder dataHolder) {
        this.dataHolder = dataHolder;

        fccDetailsPO = new DetailsPageObjects();
        fccDetailsPO.setDriver(ChromeDriver.getDriver());

        fccHomePO = new HomePageObjects();
        fccHomePO.setDriver(ChromeDriver.getDriver());
    }

    @Given("I read input file {string}")
    public void i_read_input_file(String fileName) throws Exception {
        dataHolder.readCarInput(fileName);
    }

    @When("I extract vehicle registration numbers")
    public void iExtractVehicleRegistrationNumbers() throws Exception {
        dataHolder.extractRegNumbers();
    }

    @Given("I am on Free Car Check website")
    public void iAmOnFreeCarCheckWebsite() {
        fccHomePO.EnsureFreeCarChecksPageIsOpened();
    }

    @When("I obtain the vehicle identities for the registration numbers")
    public void iObtainVehicleIdsForRegistrationNumbers() throws InterruptedException {
        for (String regNumber : dataHolder.getRegNumbers()) {
            fccHomePO.inputEnterRegistration().clear();
            fccHomePO.inputEnterRegistration().sendKeys(regNumber);
            fccHomePO.buttonFreeCarCheck().click();
            Thread.sleep(5000);

            try {
                if (fccHomePO.eltVehicleNotFound().isDisplayed()) {
                    fccHomePO.eltVehicleNotFound().click();
                    Thread.sleep(1000);
                    continue;
                }
            } catch (NoSuchElementException ignored) {}

            for (String idValue : new String[]{"Registration", "Make", "Model", "Colour", "Year"}) {
                dataHolder.getIdentities().add(fccDetailsPO.identityValue(idValue).getText());
            }

//            dataHolder.vehicleRegIdentities.put(regNumber, new ArrayList<>(dataHolder.identities));
            dataHolder.addVehicleRegIdentities(regNumber, new ArrayList<>(dataHolder.getIdentities()));
            dataHolder.getIdentities().clear();
            fccDetailsPO.CheckAnotherVehicle().click();
            Thread.sleep(2000);
        }
    }

    @Then("the obtained vehicle identities should match {string}")
    public void theObtainedVehicleIdentitiesShouldMatch(String fileName) throws FileNotFoundException, Exception {
        dataHolder.readCarOutput(fileName);

        String carOutputHeader = dataHolder.getCarOutput().get(0);
        String[] arrCarOutputHeader = carOutputHeader.split(",");

        for (Map.Entry<String, List<String>> vehicleIdentity : dataHolder.getVehicleRegIdentities().entrySet()) {
            for (int k=1; k<dataHolder.getCarOutput().size(); k++) {
                String carOutputEntry = dataHolder.getCarOutput().get(k);
                String[] arrCarOutputEntry = carOutputEntry.split(",");

                if (arrCarOutputEntry[0].equals(vehicleIdentity.getKey())) {
                    for (int j=0; j<arrCarOutputEntry.length; j++) {
                        Assert.assertEquals(arrCarOutputEntry[j], vehicleIdentity.getValue().get(j));
                        System.out.println("CarOutput.txt " + arrCarOutputHeader[j] + ": \"" + arrCarOutputEntry[j] + "\" - Vehicle Identity " + arrCarOutputHeader[j] + ": \"" + vehicleIdentity.getValue().get(j) + "\"");
                    }
                    break;
                }
            }
        }
    }
}
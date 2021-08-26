package dataHolder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataHolder {

    private String carInput;
    private List<String> carOutput;
    private final List<String> regNumbers, identities;
    private final Map<String, List<String>> vehicleRegIdentities;

    public DataHolder() {
        regNumbers = new ArrayList<>();
        identities = new ArrayList<>();
        vehicleRegIdentities = new HashMap<>();
    }

    public List<String> getRegNumbers() {
        return regNumbers;
    }

    public List<String> getIdentities() {
        return identities;
    }

    public List<String> getCarOutput() {
        return carOutput;
    }

    public void addVehicleRegIdentities(String regNumber, List<String> identities) {
        this.vehicleRegIdentities.put(regNumber, identities);
//        this.vehicleRegIdentities = vehicleRegIdentities;
    }

    public Map<String, List<String>> getVehicleRegIdentities() {
        return vehicleRegIdentities;
    }

    public void readCarInput(String fileName) throws Exception {
        byte[] encoded = Files.readAllBytes(Paths.get(fileName));
        carInput = new String(encoded, StandardCharsets.US_ASCII);
    }

    public void readCarOutput(String fileName) throws FileNotFoundException, Exception {
        carOutput = Files.readAllLines(Paths.get(fileName));
    }

    public void extractRegNumbers() throws Exception {
        Matcher m = Pattern.compile("[A-Z]{2}\\d{2}\\s*[A-Z]{3}").matcher(carInput);

        while(m.find()) {
            regNumbers.add(m.group().replaceAll(" ", ""));
        }
    }
}

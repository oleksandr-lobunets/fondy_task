package eu.fondy.task;

//import java.time.LocalDateTime;
//import java.util.Comparator;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.SortedMap;
//import java.util.TreeMap;
//import java.util.UUID;
//
//public class ChangeCalculator {
//
//    public static Map<String, Object> calculateChange(int penceSubmitted, String externalId) {
//        // Define coin denominations (pence)
//        Map<Integer, Integer> denominations = new HashMap<>();
//        denominations.put(50, 5000);
//        denominations.put(20, 2000);
//        denominations.put(10, 1000);
//        denominations.put(5, 500);
//        denominations.put(2, 200);
//        denominations.put(1, 100);
//
//        // Initialize change breakdown maps
////        SortedMap<Integer, Integer> pounds = new TreeMap<>(Comparator.reverseOrder());
////        SortedMap<Integer, Integer> pence = new TreeMap<>(Comparator.reverseOrder());
//
//        Map<Integer, Integer> pounds = new HashMap<>();
//        Map<Integer, Integer> pence = new HashMap<>();
//
//        // Calculate change for each denomination
//        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()
//                .stream().sorted((a, b) -> b.getKey() - a.getKey()).toList()) {
//            int coinValue = entry.getKey();
//            while (penceSubmitted >= entry.getValue()) {
//                penceSubmitted -= entry.getValue();
//                pounds.put(coinValue, pounds.getOrDefault(coinValue, 0) + 1);
//            }
//        }
//
//        // No remaining pence expected after the loop
//        if (penceSubmitted > 0) {
//            throw new RuntimeException("Invalid pence amount. Cannot provide exact change.");
//        }
//
//
//        // Generate current date and time
//        LocalDateTime dateTime = LocalDateTime.now();
//
//        // Construct the output map
//        Map<String, Object> response = new HashMap<>();
//        response.put("penceSubmitted", penceSubmitted);
//        response.put("externalID", externalId);
//        response.put("pounds", pounds);
//        response.put("pence", pence);
//        response.put("dateTime", dateTime.toString());
//
//        return response;
//    }
//
//    public static void main(String[] args) {
//        String externalId = UUID.randomUUID().toString(); // Generate a unique ID
//        int penceSubmitted = 50122;
//        Map<String, Object> response = calculateChange(penceSubmitted, externalId);
//        System.out.println(response);
//    }

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ChangeCalculator {

    public static void main(String[] args) {
        int penceSubmitted = 50122;
        String externalID = "UUID_1";

        Map<Integer, Integer> pounds = new HashMap<>();
        Map<Integer, Integer> pence = new HashMap<>();

        // Populate denominations for pounds and pence
        for (int denomination : new int[] {50, 20, 10, 5, 2, 1}) {
            pounds.put(denomination, 0);
            pence.put(denomination, 0);
        }

        // Calculate change in pounds and pence
        int poundsValue = penceSubmitted / 100;
        int penceValue = penceSubmitted % 100;

        // Distribute pounds
        int remainingPounds = poundsValue;
        for (int denomination : new int[] {50, 20, 10, 5, 2, 1}) {
            int count = remainingPounds / denomination;
            pounds.put(denomination, count);
            remainingPounds -= count * denomination;
        }

        // Distribute pence
        int remainingPence = penceValue;
        for (int denomination : new int[] {50, 20, 10, 5, 2, 1}) {
            int count = remainingPence / denomination;
            pence.put(denomination, count);
            remainingPence -= count * denomination;
        }

        // Print or return the JSON response
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("{"
                + "\"penceSubmitted\":" + penceSubmitted + ","
                + "\"externalID\":\"" + externalID + "\","
                + "\"pounds\":" + pounds.toString() + ","
                + "\"pence\":" + pence.toString() + ","
                + "\"dateTime\":\"" + dateTime + "\""
                + "}");
    }

}

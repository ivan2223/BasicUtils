package deloitte.basic.utils;

import java.util.HashMap;

public class AverageCalculator {
    private final String name, grade;
    private final HashMap<String, Double> hashMap;

    public AverageCalculator(String name, String grade){
        this.name = name;
        this.grade = grade;
        this.hashMap = new HashMap<>();
    }

    public void assignSignatures(String signature, double score){
        hashMap.put(signature, score);
    }

    public void printSignatures() {
        String separator = "-".repeat(50);

        double average = hashMap.values().stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);

        String status;
        if(average > 8)
            status = "Passed with good level";
        else if(average > 6)
            status = "Passed";
        else
            status = "Failed";

        System.out.println(separator);
        System.out.printf("Student Name: %s \t Grade: %s%n", name, grade);
        System.out.println(separator);

        hashMap.forEach((subject, score) ->
                System.out.printf("Signature: %-15s \t Score: %.2f%n", subject, score));

        System.out.println(separator);
        System.out.printf("Final Average: %.2f  \t Status: %s%n", average, status);
    }

}

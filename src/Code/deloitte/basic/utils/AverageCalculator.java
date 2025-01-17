package deloitte.basic.utils;

import java.util.HashMap;

public class AverageCalculator {
    private final HashMap<String, Double> hashMap;
    private final String name, grade;

    public AverageCalculator(String name, String grade){
        this.hashMap = new HashMap<>();
        this.grade = grade;
        this.name = name;
    }

    public void assignSignatures(String signature, double score){
        hashMap.put(signature, score);
    }

    public String getName(){
        return this.name;
    }

    public String getGrade(){
        return this.grade;
    }

    public double getAverage(){
        return hashMap.values().stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }

    public String getStatus(){
        String status;
        double average = getAverage();
        if(average > 8)
            status = "Passed with good level";
        else if(average > 6)
            status = "Passed";
        else
            status = "Failed";
        return status;
    }

    public HashMap<String, Double> getHashMap() {
        return hashMap;
    }

}

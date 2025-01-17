package deloitte.basic.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.IntStream;

public class UserInterface {
    private static final String path = "src/ConsoleLogs.txt";
    private static List<String> list;
    private final Scanner reader;
    private final Calculator calculator;
    private final Encoder encoder;
    private AverageCalculator averageC;


    public UserInterface(){
        try {

            list = Arrays.stream(Files.readString(Path.of(path))
                    .split("Method\\."))
                    .map(String::trim)
                    .toList();

            this.reader = new Scanner(System.in);
            this.calculator = new Calculator();
            this.encoder = new Encoder();

        } catch (IOException e) {
            System.err.println("Error: Unable to read the file at the specified path.");
            throw new RuntimeException("Initialization failed due to file error.", e);
        }
    }

    public void mainMenu() {
        boolean continueRunning = true;

        while (continueRunning) {
            try {
                System.out.println(list.get(1));

                int input = reader.nextInt();

                continueRunning = handleMenuOption(input);
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                reader.nextLine();
            }
        }

    }

    private boolean handleMenuOption(int input) {
        return switch (input) {
            case 1 -> {
                calculator();
                yield true;
            }
            case 2 -> {
                encoder();
                yield true;
            }
            case 3 -> {
                average();
                yield true;
            }
            case 4 -> {
                System.out.println("Exiting the program...");
                yield false;
            }
            default -> {
                System.out.println("Not a valid option. Please try again.");
                yield true;
            }
        };
    }

    private void calculator() {
        try {
            System.out.println(list.get(2));
            int input = reader.nextInt();
            ArrayList<BigDecimal> res = enterVal();
            switch (input){
                case 1:
                    System.out.println(calculator.sum(res.getFirst(), res.getLast()));
                    break;
                case 2:
                    System.out.println(calculator.subtract(res.getFirst(), res.getLast()));
                    break;
                case 3:
                    System.out.println(calculator.multiply(res.getFirst(), res.getLast()));
                    break;
                case 4:
                    System.out.println(calculator.divide(res.getFirst(), res.getLast()));
                    break;
            }

        } catch (Exception e) {
            System.out.println("Was expecting a number");
            reader.nextLine();
        }
    }


    private ArrayList<BigDecimal> enterVal(){
        String[] array = list.get(3).lines().toArray(String[]::new);
        ArrayList<BigDecimal> result = new ArrayList<>();
        int count = 1;

        for(String s : array){
            System.out.println(s);
            if (count != array.length)
                result.add(reader.nextBigDecimal());
            count++;
        }

        return result;
    }


    private void encoder() {
        try {
            System.out.println(list.get(4));
            int in = reader.nextInt();
            reader.nextLine();
            switch (in){
                case 1:
                    System.out.println(list.get(5));
                    System.out.println("Encoded String is: " + encoder.encoder(reader.nextLine()));
                break;
                case 2:
                    System.out.println(list.get(6));
                    System.out.println("Decoded String is: " + encoder.decode(reader.next()));
                break;
                default:
                    System.out.println("Not an option");
                break;
            }
        } catch (Exception ignored){
            System.out.println("Was expecting a number");
        }

    }

    private void average() {
        try {
            reader.nextLine();

            String[] strStream = list.get(7).lines()
                    .map(s -> {
                        System.out.println(s);
                        return reader.nextLine();
                    })
                    .toArray(String[]::new);
            averageC = new AverageCalculator(strStream[0], strStream[1]);


            IntStream.range(0, Integer.parseInt(strStream[2]))
                    .forEach(num -> {
                        System.out.println(list.get(8).replace("#", String.valueOf(num + 1)));
                        String name = reader.nextLine();
                        System.out.println(list.get(9).replace("#", name));
                        averageC.assignSignatures(name, reader.nextDouble());
                        reader.nextLine();
                    });


            HashMap<String, Double> hashMap = averageC.getHashMap();
            String separator = "-".repeat(50);
            list.get(10)
                    .lines()
                    .forEach(s -> {
                        System.out.println(separator);
                        if (s.contains("Signature:")){
                            hashMap.forEach((key, value) ->{
                                System.out.println(s.replace("#", key).replace("%", String.valueOf(value)));
                            });
                        } else if(s.contains("Student Name:")){
                            System.out.println(s.replace("#", averageC.getName()).replace("%", averageC.getGrade()));
                        } else {
                            System.out.println(s.replace("#", averageC.getStatus()).replace("%", String.valueOf(averageC.getAverage())));
                        }
                    });

        } catch (Exception e){
            System.out.println(e);
        }
    }


}

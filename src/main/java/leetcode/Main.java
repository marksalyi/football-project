package leetcode;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        String num = "1210";
        // System.out.println(checker(num));
        System.out.println(checker2(num));

    }
    public static boolean checker(String num){
        String[] numArr = num.split(""); // O(n)
        //String[] numArr = Arrays.stream(num.split("")).toList(); // O(n)
        List<String> numArrayList = new ArrayList<>();
        numArrayList.addAll(Arrays.asList(numArr)); // O(n) - duplicate step

        for (int i = 0; i < numArrayList.size(); i++){
            int counter = i;
            String counterString = Integer.toString(counter);
            int peeker = 0;

            for (int j = 0; j < numArrayList.size(); j++){
                if (numArrayList.get(j).equals(counterString)){
                    peeker++;
                }
            } // Not needed O(n2)
            if(peeker < numArrayList.size()){
                System.out.println("The digit: " + counter + " occurs in array: " + peeker + "times.");
            }
            String peekerString = Integer.toString(peeker);
            if(peekerString.equals(numArrayList.get(i))){
                System.out.println("it is okay");
            } else {
                System.out.println("failed");
                return false;
            }
        }
        return true;
    }

    public static boolean checker2(String num){
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (char c : num.toCharArray()) {
            int digit = Character.getNumericValue(c);
            System.out.println("digit erteke: " + digit);
            frequencyMap.put(digit, frequencyMap.getOrDefault(digit, 0) + 1);
        }


        for (int i = 0; i < num.length(); i++) {
            int expectedFrequency = Character.getNumericValue(num.charAt(i));
            System.out.println("expected frequency: " + expectedFrequency);
            int actualFrequency = frequencyMap.getOrDefault(i, 0);
            System.out.println("actual frequency: " + actualFrequency);

            if (expectedFrequency != actualFrequency) {
                System.out.println("failed");
                return false;
            }
        }
        System.out.println("frequency map: " + frequencyMap);
        System.out.println("it is okay");
        return true;
    }
}

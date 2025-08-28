package second_task;

import java.util.HashMap;
import java.util.Map;

public class RepCounter {

    public static <T> Map<T, Integer> countRepetition(T[] inputArray) {
        Map<T, Integer> result = new HashMap<>();

        for (T element : inputArray) {
            result.put(element, result.getOrDefault(element, 0) + 1);
        }

        return result;
    }

    public static void main(String[] args) {

        Integer[] arrInt = {1, 2, 3, 4, 2, 1, 3, 4, 5, 1};
        System.out.println(RepCounter.countRepetition(arrInt)); // {1=3, 2=2, 3=2, 4=2, 5=1}

        String[] arrStr = {"red", "blue", "red", "green", "yellow", "black", "green", "blue", "red"};
        System.out.println(RepCounter.countRepetition(arrStr)); // {red=3, green=2, blue=2, yellow=1, black=1}
    }
}

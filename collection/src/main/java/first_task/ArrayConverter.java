package first_task;

import java.util.Arrays;

public class ArrayConverter {

    public static <T> T[] filter(T[] inputArray, Class<? extends Filter<T>> fClass) {
        try {
            Filter<T> filterInstance = fClass.getDeclaredConstructor().newInstance();

            T[] outputArray = Arrays.copyOf(inputArray, inputArray.length);

            for (int i = 0; i < inputArray.length; i++) {
                outputArray[i] = filterInstance.apply(inputArray[i]);
            }

            return outputArray;

        } catch (ReflectiveOperationException e) {
            throw new IllegalArgumentException("Cannot create filter", e);
        }
    }
}

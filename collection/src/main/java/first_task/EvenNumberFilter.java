package first_task;

import java.util.Arrays;

public class EvenNumberFilter implements Filter<Integer> {

    @Override
    public Integer apply(Integer n) {
        return  (n == null || n % 2 != 0) ? null : n;
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, null, 5, 6, null, 8};
        var changedArr = ArrayConverter.filter(arr, EvenNumberFilter.class);
        System.out.println(Arrays.toString(changedArr));
    }
}

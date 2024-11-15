package org.fotm.java8.consumer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BinarySearch {

    public static Integer search(List<Integer> nums, Integer guess) {
        System.out.println("guess " + guess + ", nums size " + nums.size() + ", split " + nums.size() / 2);
        var a = nums.stream()
                    .map(Object::toString)
                    .reduce("", (x, y) -> x.isEmpty() ? y : String.join(", ", x, y));
        if (!a.isEmpty()) System.out.println("    " + a);
        // list is empty or guess is out of range
        if (nums.isEmpty() || guess > nums.get(nums.size() - 1) || guess < nums.get(0)) {
            return null;
        }
        Integer result = null;
        int split = (nums.size()) / 2;
        Integer current = nums.get(split);
        if (guess == current) {
            // found it
            result = guess;
        } else if (guess < current) {
            // check lower side
            result = search(nums.subList(0, split), guess);
        } else if (guess > current) {
            // check higher side
            result = search(nums.subList(split, nums.size()), guess);
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(-1, 0, 1, 2, 3, 42, 54, 99);
        System.out.println("    empty list " + search(Collections.emptyList(), 42));
        System.out.println("    below range " + search(list, list.get(0) - 1));
        System.out.println("    min range " + search(list, list.get(0)));
        System.out.println("    not found " + search(list, 30));
        System.out.println("    max range " + search(list, list.get(list.size() - 1)));
        System.out.println("    greater than range " + search(list, list.get(list.size() - 1) + 1));
    }
}

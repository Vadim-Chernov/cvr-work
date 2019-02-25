package cvr.otus.utils;

import java.util.Arrays;

public class Util {
    public static int[] StringToIntArray(String str, String del) {
        String[] strArr = str.split(del);
        int[] nums = new int[strArr.length];
        int count = 0;
        for (String s : strArr)
            nums[count++] = Integer.parseInt(s);

        return nums;
    }

    private static boolean elementExists(int elem, int[] arr) {
        for (int e : arr)
            if (e == elem)
                return true;

        return false;
    }

    public static double examineArrays(int[] a1, int[] res) {
        double ret = 0;
        int length = res.length;
        if (a1.length > res.length) {
            length = a1.length;
        } else length = res.length;
        double delta = 5d/length;

        for (int elem : a1) {
            if (elementExists(elem, res))
                ret += delta;
        }
        return ret;
    }


    public static void main(String[] args) {
        int[] a1 = {1,4};
        int[] res = {4};
        double compare = examineArrays(a1, res);
        System.out.println(compare);
    }
}
